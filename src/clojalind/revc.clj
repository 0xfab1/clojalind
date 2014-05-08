(require '[clojure.string :as string])

(let [input (string/upper-case (slurp "../../data/revc.in"))
      dict (zipmap "ACGT" "TGCA")]
  (println (apply str (reverse (map dict input)))))