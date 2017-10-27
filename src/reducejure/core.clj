(ns reducejure.core
  (:refer-clojure :only [defn fn if
                         case
                         reduce + =]))

(defn first
  [xs]
  (reduce (fn ([]) ; empty coll case
              ([e & _] e))
            xs))

(defn ffirst
  [xs]
  (first (first xs)))

(defn second
  [xs]
  (first
    (reduce (fn [[e ok] x]
              (case ok
                2 [e ok]
                1 [x 2]
                0 [nil 1]))
            [nil 0] xs)))

(defn count
  [xs]
  (reduce (fn [acc & _] (+ acc 1)) 0 xs))

(defn contains? ; note it doesn't work on maps
  [xs k]
  (reduce (fn [ok? x]
            (if ok? ok? (= k x)))
          false xs))

; missing: conj
#_
(defn vec
  [xs]
  (reduce conj [] xs))

#_
(defn rest
  [xs]
  (reduce (fn [acc x]
            (if acc
              (conj acc x)))
          nil
          xs))
