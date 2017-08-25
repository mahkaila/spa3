
# coding: utf-8

# In[ ]:

get_ipython().system('mkdir inputs/wes')
command1 = "ffmpeg -i inputs/darjeeling.mp4 -vf fps=24 inputs/wes/darjeeling_%04d.jpg -hide_banner"
command2 = "ffmpeg -i inputs/moonrise.mp4 -vf fps=24 inputs/wes/moon_%04d.jpg -hide_banner"
command3 = "ffmpeg -i inputs/rushmore.mp4 -vf fps=24 inputs/wes/rushmore_%04d.jpg -hide_banner"
command4 = "ffmpeg -i inputs/budapest.mp4 -vf fps=24 inputs/wes/budapest_%04d.jpg -hide_banner"
command5 = "ffmpeg -i inputs/fox.mp4 -vf fps=24 inputs/wes/fox_%04d.jpg -hide_banner"
get_ipython().system('{command1}')
get_ipython().system('{command2}')
get_ipython().system('{command3}')
get_ipython().system('{command4}')
get_ipython().system('{command5}')

