(ns reducejure.core-test
  (:require [clojure.test :refer :all]
            [reducejure.core :as r]))

(deftest rfirst
  (are [expected ls] (= expected (r/first ls))
    nil nil
    nil []
    nil [nil]
    false [false]
    1 [1 2 3 4 5]
    2 '(2 3 1)
    [:a 1] {:a 1}))

(deftest rffirst
  (are [expected ls] (= expected (r/ffirst ls))
    nil nil
    nil []
    nil [nil]
    nil [[nil]]
    false [[false]]
    1 [[1 2 3] [4] 5]
    [1] [[[1] 2] 3]
    :a {:a 1}))

(deftest rsecond
  (are [expected ls] (= expected (r/second ls))
    nil nil
    nil []
    nil [nil]
    nil [nil nil]
    nil [true nil]
    false [true false]
    2 [1 2 3 4 5]
    3 '(2 3 1)))

(deftest rcount
  (are [expected ls] (= expected (r/count ls))
    0 nil
    0 []
    0 {}
    1 [2]
    4 [1 2 3 4]))

(deftest rcontains?
  (are [expected ls k] (= expected (r/contains? ls k))
    false nil 1
    false nil nil
    false [] nil
    false [] 1
    false [2 3 4] 1
    true [1] 1
    true [2 3 4 5 1] 1))

(deftest rempty?
  (are [expected ls] (= expected (r/empty? ls))
    true nil
    true []
    true '()
    true {}
    true ""
    false [1]
    false [1 2]
    false {nil nil}))
