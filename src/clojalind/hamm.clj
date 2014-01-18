(use 'clojure.java.io)
(use '[clojure.string :only [join upper-case]])

(defn ham
  ([] 0)
  ([fst snd & more] (+ (if (= fst snd) 0 1) (apply ham more))))

(with-open [rdr (reader "../../data/hamm.in")]
  (let [lines (line-seq rdr)
        fst (first lines)
        snd (second lines)
        mix (interleave fst snd)]
    (prn (apply ham mix))))