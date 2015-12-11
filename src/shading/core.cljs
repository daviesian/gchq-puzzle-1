(ns shading.core
  (:require [reagent.core :as reagent :refer [atom]]
            [shading.spec :as spec]
            [clojure.string :as str]))

(defn new-world []
  {:rows spec/initial-grid})


(defonce app-state (atom (new-world)))
(defn reset-app-state []
  (reset! app-state (new-world)))

(defn toggle [x y]
  (swap! app-state (fn [s]
                     (assoc s :rows (map-indexed (fn [j row]
                                                   (if (= j y)
                                                     (map-indexed (fn [i shaded?]
                                                                    (if (and (= i x) (not= shaded? 1))
                                                                      (not shaded?)
                                                                      shaded?)) row)
                                                     row)) (:rows s))))))

(defn get-run-lengths [s]
  (let [partitions (partition-by boolean s)
        runs (filter #(first %) partitions)
        run-lengths (map count runs)]
    run-lengths))

(defn get-row-run-lengths [y]
  (get-run-lengths (nth (:rows @app-state) y)))

(defn get-col-run-lengths [x]
  (get-run-lengths (map #(nth % x) (:rows @app-state))))

(defn world []
  (let [cell-width (count spec/cols)
        cell-height (count spec/rows)
        max-spec-width (apply max (map count spec/rows))
        max-spec-height (apply max (map count spec/cols))]
    [:div
     [:h1 "Shading"]
     [:h2 "GCHQ Christmas Puzzle 2015. Built with Reagent and FigWheel"]
     [:svg {:style    {:border     "none"
                       :background "transparent"
                       :font-family "sans-serif"
                       :width      1000
                       :height     1000}
            :view-box (str/join " " [0 0 (+ cell-width max-spec-width) (+ cell-height max-spec-height)])}
      (doall (map-indexed (fn [y spec]
                            (let [rls (get-row-run-lengths y)]
                              (map-indexed (fn [i run-length]
                                             (let [match (= (nth rls i nil) run-length)]
                                               (list [:text {:x           (+ (- max-spec-width (count spec)) i 0.5)
                                                             :y           (+ max-spec-height y 0.75)
                                                             :font-size   0.6
                                                             :text-anchor "middle"
                                                             :fill        (if match "green" "red")
                                                             :key         (str "row-rule-" i)}
                                                      run-length]
                                                     nil)
                                               )) spec))) spec/rows))
      (doall (map-indexed (fn [x spec]
                            (let [rls (get-col-run-lengths x)]
                              (map-indexed (fn [i run-length]
                                             (let [match (= (nth rls i nil) run-length)]
                                               (list [:text {:x           (+ max-spec-height x 0.5)
                                                             :y           (+ (- max-spec-height (count spec)) i 0.75)
                                                             :font-size   0.6
                                                             :text-anchor "middle"
                                                             :fill        (if match "green" "red")
                                                             :key         (str "row-rule-" i)}
                                                      run-length]
                                                     nil))) spec))) spec/cols))
      (map-indexed (fn [y row]
                     (map-indexed (fn [x shaded?]
                                    [:rect {:x            (+ max-spec-width x)
                                            :y            (+ max-spec-height y)
                                            :width        1
                                            :height       1
                                            :stroke       "black"
                                            :stroke-width 0.01
                                            :rx           0.1
                                            :fill         (condp = shaded?
                                                            true "#000"
                                                            1 "#008"
                                                            "#ffc")
                                            :key          (str "cell-" x "-" y)
                                            :on-click     (partial toggle x y)}]) row)) (:rows @app-state))]]))




(enable-console-print!)

(println "Edits to this text should show up in your developer console.")

;; define your app data so that it doesn't get over-written on reload


(reagent/render-component [world]
                          (. js/document (getElementById "app")))


(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  (swap! app-state update-in [:__figwheel_counter] inc))
