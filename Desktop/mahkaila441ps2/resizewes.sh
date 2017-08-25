#!/bin/bash

# show commands and stop if there is an error
set -ex

# make the directory if it is not there
mkdir -p temp/wes_resized

# this command resizes to 128x128, chopping what doesn't fit
mogrify \
  -path temp/wes_resized \
  -thumbnail 160x160^ \
  -background black \
  -gravity center \
  -extent 160x160 \
  'inputs/wes/*.jpg'