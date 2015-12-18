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

(defn decode [keyword message]
  "decodeme")

(defn decipher [cipher message]
  "decypherme")

