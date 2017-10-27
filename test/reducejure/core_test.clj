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
