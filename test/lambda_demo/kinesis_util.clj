(ns lambda-demo.kinesis-util
  (:require [lambda-demo.env :refer [setup]]
            [amazonica.aws.kinesis :as kinesis])
  (:import [java.nio ByteBuffer]))

(defn insert-test-data [prefix how-many]
  (dotimes [_ how-many]
    (kinesis/put-record {:endpoint (:region setup)}
                        (:kinesis-stream setup)
                        (ByteBuffer/wrap (.getBytes (str prefix "-" (rand-int 100))))
                        "partition-key")))
