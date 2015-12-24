
(ns alphabet-cipher.coder)

(defn- rotate-coll [positions coll]
  (let [n (mod positions (count coll))]
    (concat (drop n coll) (take n coll))))

(def alphabet
  (map char (range (int \a) (inc (int \z)))))

(def alphabet-keys
  (for [colum alphabet]
    (for [row alphabet]
      (keyword (str colum row)))))

(def rotated-alphabets
  (for [rotation (range (count alphabet))]
    (rotate-coll rotation alphabet)))

(def encoding-map
  (into {} (mapcat #(zipmap %1 %2) alphabet-keys rotated-alphabets)))

(defn encode [keyword message]
  (let [colum-word (take (count message) (cycle (seq keyword)))]
    (apply str
           (map #(% encoding-map)
                (map #(clojure.core/keyword (str %1 %2))
                     (seq colum-word) (seq message))))))

(def decoding-keys
  (for [rotation (range (count alphabet))]
    (map #(keyword (str %1 %2)) alphabet (rotate-coll rotation alphabet))))

(def decoding-values
  (for [ch alphabet] (repeat (count alphabet) ch)))

(def decoding-map
  (zipmap (flatten decoding-keys) (flatten decoding-values)))

(defn decode [keyword message]
  (let [colum-word (take (count message) (cycle (seq keyword)))]
    (apply str
           (map #(% decoding-map)
                (map #(clojure.core/keyword (str %1 %2))
                     (seq colum-word) (seq message))))))

(defn decipher [cipher message]
  "decypherme")

