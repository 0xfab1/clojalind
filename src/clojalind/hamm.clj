(ns clojalind.hamm
  (:require [clojure.string :as string]
            [clojure.java.io :as io]))

(defn hamm
  ([] 0)
  ([p q & more]
   (+ (if (= p q) 0 1) (apply hamm more))))

(with-open [rdr (io/reader "data/hamm.in")]
  (let [lines (line-seq rdr)
        [fst snd & _] lines  ; take only the first two entries
        mix (interleave fst snd)]
    (println (apply hamm mix))))
