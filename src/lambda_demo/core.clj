(ns lambda-demo.core
  (:require [uswitch.lambada.core :refer [deflambdafn]]))

(deflambdafn lambda-demo.LambdaFn
  [in out ctx]
  (println "Hello from Lambda"))
