(ns clojalind
  (:require [clojure.string :as string]))

(let [input (string/upper-case (slurp "data/rna.in"))]
  (println (string/replace input \T \U)))
