;; Rules
;; -----
;;
;; 1. Import as few core functions as possible
;; 2. Non-sequence-related functions are allowed (but see 1.)
(ns reducejure.core
  (:refer-clojure :only [defn fn
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

(defn empty?
  [xs]
  (reduce (fn [& _] false)
          true xs))

(defn every? ;; TEST ME
  [pred xs]
  (reduce (fn [ok? x]
            (if ok? (pred x)))
          true xs))

(defn not-every? ;; TEST ME
  [pred xs]
  ;; TODO use reduce
  (if (every? pred xs)
    false
    true))

;; TODO
;;  some not-any?
;;  next
;;  nfirst
;;  fnext
;;  nnext
;;  nth
;;  nthnext
;;  nthrest
;;  butlast
;;  take
;;  take-last
;;  take-nth
;;  take-while
;;  drop
;;  drop-last
;;  drop-while
;;  concat
;;  group-by
;;  distinct
;;  partition
;;  partition-by
;;  partition-all
;;  split-at
;;  split-with
;;  filter
;;  filterv
;;  remove
;;  replace
;;  flatten
;;  sort
;;  reverse
;;  dedupe
;;  map
;;  keep
;;  mapcat

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
