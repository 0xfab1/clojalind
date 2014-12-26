(ns clojalind.dna
  (:require [clojure.string :as string]))

(let [input (string/upper-case (slurp "data/dna.in"))
      freqs (frequencies input)]
    (println (string/join " " (map freqs "ACGT"))))
