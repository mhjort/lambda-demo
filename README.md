# lambda-demo

A Clojure AWS Lambda demo.

Reads events from Kinesis stream and stores them in S3.

## Installation

Note! At the moment this demo setup works only in Amazon EU Ireland (eu-west-1) so all setup must be done there

* Create IAM role with following rights: Lambda, Kinesis read, Cloudwatch log write, S3 write
* Create Kinesis stream with name "test"
* Build "zip" file

```
lein uberjar
```

* Create Lambda function with name "LambdaDemo", uberjar as zip file, handlers as "lambda-demo.LambdaFn", role the one you created before
* Set "test" Kinesis stream as an event source
* Enter some test data via repl

```
(use 'lambda-demo.kinesis-util)

(insert-test-data "some-data" 50)
```

## License

Copyright © 2015 FIXME

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
