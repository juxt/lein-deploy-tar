# lein-deploy-tar

A Leiningen plugin to upload tars to a Maven repository.

## Usage

Put `[lein-tar "3.3.0"]` and `[lein-deploy-tar "0.1.0"]` into the `:plugins` vector of your `project.clj`.

Create the tar with

    $ lein tar

Deploy the tar with

    $ lein deploy-tar

## License

Copyright © 2015 JUXT LTD.

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
