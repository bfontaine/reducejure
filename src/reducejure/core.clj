;; Rules
;; -----
;;
;; 1. Import as few core functions as possible
;; 2. Non-sequence-related functions are allowed (but see 1.)
(ns reducejure.core
  (:refer-clojure :only [defn fn
                         case
                         reduce
                         conj
                         + =]))

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
  (reduce (fn [ok x]
            (if ok ok (= k x)))
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

(defn not
  [x]
  (if x false true))

(defn not-every?
  [pred xs]
  (not (every? pred xs)))

(defn some ;; TEST ME
  [pred xs]
  (reduce (fn [ok x]
            (if ok ok (pred x)))
          nil xs))

(defn reverse ;; TEST ME
  [xs]
  (reduce conj '() xs))

(defn vec ;; TEST ME
  [xs]
  (reduce conj [] xs))

(defn rest ;; TEST ME
  [xs]
  (reduce (fn [acc x]
            (if acc
              (conj acc x)))
          nil
          xs))

(defn butlast ;; TEST ME
  [xs]
  (reverse (rest (reverse xs))))

(defn filter ;; TEST ME
  [pred xs]
  (reduce (fn [acc x]
            (if (pred x)
              (conj acc x)
              acc))
          [] xs))

(defn remove ;; TEST ME
  [pred xs]
  (reduce (fn [acc x]
            (if (pred x)
              acc
              (conj acc x)))
          [] xs))

(defn map ;; TEST ME
  [f xs]
  (reduce (fn [acc x]
            (conj acc (f x)))
          []  xs))

(defn keep ;; TEST ME
  [f xs]
  (reduce (fn [acc x]
            ; avoid a let
            ((fn [e]
               (if (= nil e)
                 acc
                 (conj acc e)))
             (f x)))
          [] xs))

;; TODO
;;  not-any?
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
;;  filterv
;;  replace
;;  flatten
;;  sort
;;  dedupe
;;  mapcat
