#!/bin/bash

# show commands and stop if there is an error
set -ex

SEARCH_STRING="blueberry%20muffins"

# get 5 pages
for PAGE in {1..5}
do
    ### Uncomment one of the following two (either group or tag)

    # this is an example with a group
    # URL='https://www.flickr.com/groups/'$SEARCH_STRING'/pool/page'$PAGE

    # this is an example with tags
    URL='https://www.flickr.com/photos/tags/'$SEARCH_STRING'/page'$PAGE

    echo "-------> about to fetch URL: " $URL
    sleep 1

    # fetch the images
    wget --adjust-extension \
         --random-wait \
         --limit-rate=100k \
         --span-hosts \
         --convert-links \
         --backup-converted \
         --no-directories \
         --timestamping \
         --page-requisites \
         --directory-prefix=inputs/flickr_downloads \
         --execute robots=off \
         --accept=.jpg \
         $URL

# other unused arguments
         # --recursive \
         # --level 1 \
         # --domains en.wikipedia.org \
     
done
