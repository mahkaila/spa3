#!/bin/bash

# show commands and stop if there is an error
set -ex

CUDA_VISIBLE_DEVICES="" \
  python /usr/local/anaconda/extras/smartgrid.py \
  --model color_lab \
  --input-glob 'temp/wes_resized/*' \
  --aspect-ratio 1.92 \
  --drop-to-fit \
  --output-path wes_colorgrid 
