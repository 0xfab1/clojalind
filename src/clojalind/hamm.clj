(use 'clojure.java.io)
(use 'clojure.string)

(defn ham
  [arr]
  (if (empty? arr)
    0
    (let [fst (first arr)
          snd (second arr)
          more (drop 2 arr)]
      (+ (if (= fst snd) 0 1) (ham more)))))

(with-open [rdr (reader "../../data/hamm.in")]
  (let [lines (line-seq rdr)
        fst (first lines)
        snd (second lines)
        mix (interleave fst snd)]
    (prn (ham mix))))