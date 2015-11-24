(ns leiningen.deploy-tar
  (:require
   [clojure.java.io :as io]
   [leiningen.deploy :as deploy]
   [clojure.pprint :refer [pprint]]
   [cemerick.pomegranate.aether :as aether]))

(defn get-repo [project]
  (if (re-matches #".+SNAPSHOT" (:version project))
    "snapshots"
    "releases"))

(defn deploy-tar
  "Deploy a tar file to a Maven repository"
  [project & args]
  (let [tarfile (io/file (format "%s/%s-%s.tar" (:target-path project) (:name project) (:version project)))]
    (if (.exists tarfile)
      (do
        (println "Deploying tar file:" (str tarfile))
        (aether/deploy
         :coordinates [(symbol (:group project) (:name project)) (:version project)]
         :artifact-map {[:extension "tar"] tarfile}
         :transfer-listener :stdout
         :repository [(deploy/repo-for project (get-repo project))]))
      (throw (ex-info (format "Failed to find tarfile, have you called lein tar? %s" tarfile) {})))))
