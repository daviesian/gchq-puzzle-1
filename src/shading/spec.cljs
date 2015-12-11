(ns shading.spec)

(def rows [[7 3 1 1 7]
           [1 1 2 2 1 1]
           [1 3 1 3 1 1 3 1]
           [1 3 1 1 6 1 3 1]
           [1 3 1 5 2 1 3 1]
           [1 1 2 1 1]
           [7 1 1 1 1 1 7]
           [3 3]
           [1 2 3 1 1 3 1 1 2]
           [1 1 3 2 1 1]
           [4 1 4 2 1 2]
           [1 1 1 1 1 4 1 3]
           [2 1 1 1 2 5]
           [3 2 2 6 3 1]
           [1 9 1 1 2 1]
           [2 1 2 2 3 1]
           [3 1 1 1 1 5 1]
           [1 2 2 5]
           [7 1 2 1 1 1 3]
           [1 1 2 1 2 2 1]
           [1 3 1 4 5 1]
           [1 3 1 3 10 2]
           [1 3 1 1 6 6]
           [1 1 2 1 1 2]
           [7 2 1 2 5]])

(def cols [[7 2 1 1 7]
           [1 1 2 2 1 1]
           [1 3 1 3 1 3 1 3 1]
           [1 3 1 1 5 1 3 1]
           [1 3 1 1 4 1 3 1]
           [1 1 1 2 1 1]
           [7 1 1 1 1 1 7]
           [1 1 3]
           [2 1 2 1 8 2 1]
           [2 2 1 2 1 1 1 2]
           [1 7 3 2 1]
           [1 2 3 1 1 1 1 1]
           [4 1 1 2 6]
           [3 3 1 1 1 3 1]
           [1 2 5 2 2]
           [2 2 1 1 1 1 1 2 1]
           [1 3 3 2 1 8 1]
           [6 2 1]
           [7 1 4 1 1 3]
           [1 1 1 1 4]
           [1 3 1 3 7 1]
           [1 3 1 1 1 2 1 1 4]
           [1 3 1 4 3 3]
           [1 1 2 2 2 6 1]
           [7 1 3 2 1 1]])

(def initial-grid [(repeat 25 false)
                   (repeat 25 false)
                   (repeat 25 false)
                   (concat (repeat 3 false) [1 1] (repeat 7 false) [1 1] (repeat 7 false) [1] (repeat 3 false))
                   (repeat 25 false)
                   (repeat 25 false)
                   (repeat 25 false)
                   (repeat 25 false)
                   (concat (repeat 6 false) [1 1 false false 1] (repeat 3 false) [1 1] (repeat 2 false) [1] (repeat 6 false))
                   (repeat 25 false)
                   (repeat 25 false)
                   (repeat 25 false)
                   (repeat 25 false)
                   (repeat 25 false)
                   (repeat 25 false)
                   (repeat 25 false)
                   (concat (repeat 6 false) [1] (repeat 4 false) [1] (repeat 4 false) [1] (repeat 3 false) [1] (repeat 4 false))
                   (repeat 25 false)
                   (repeat 25 false)
                   (repeat 25 false)
                   (repeat 25 false)
                   (concat (repeat 3 false) [1 1] (repeat 4 false) [1 1] (repeat 4 false) [1] (repeat 4 false) [1 1] (repeat 3 false))
                   (repeat 25 false)
                   (repeat 25 false)
                   (repeat 25 false)])