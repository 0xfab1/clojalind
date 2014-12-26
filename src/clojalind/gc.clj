(ns clojalind.gc
  (:require [clojure.java.io :as io]
            [clojalind.utils :as utils]))

(defn calc-gc
  "Calculates GC% content given a DNA string"
  [seq]
  (let [gc-1 #(case % \G 1 \C 1 0)
        gc (apply + (map gc-1 seq))]
    (->> seq count (/ gc) (* 100.0))))

(defn augment [fasta]
  (map (fn [fa] (->> (:seq fa)
                     calc-gc (assoc fa :gc))) fasta))

(with-open [rdr (io/reader "data/gc.in")]
  (let [fasta (utils/read-fasta (line-seq rdr))
        calc-entries (augment fasta)
        max-entry (apply max-key :gc calc-entries)]
    (println (:name max-entry))
    (println (:gc max-entry))))
