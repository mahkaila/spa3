set -ex

ipython wes_loadimages.py
bash resizewes.sh
bash wes_colorgrid.sh