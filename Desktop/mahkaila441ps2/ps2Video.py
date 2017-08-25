
# coding: utf-8

# In[1]:

from IPython.display import Image
from IPython.display import HTML


# In[2]:

get_ipython().system('mkdir inputs/drake')
get_ipython().system('mkdir outputs/drake')
get_ipython().system('mkdir outputs/drake/drake_comp')
command = "ffmpeg -i inputs/bling.mp4 -vf fps=24 inputs/drake/img_%04d.jpg -hide_banner"
get_ipython().system('{command}')


# In[3]:

get_ipython().system('mkdir temp/drake')
get_ipython().system('mkdir temp/drake/sketch_drake1')
get_ipython().system('mkdir temp/drake/drake_comp')
get_ipython().system('mkdir temp/drake/crop_drake')
get_ipython().system('mkdir temp/drake/drake_alpha')
get_ipython().system('mkdir temp/drake/drake_sketch')
get_ipython().system('mkdir temp/drake/drake_dark')
get_ipython().system('mkdir temp/drake/drake_light')
get_ipython().system('mkdir temp/drake/drake_line')
get_ipython().system('mkdir temp/drake/drake_norm')
get_ipython().system('mkdir temp/drake/sketchcrop_drake')


# In[4]:

countNum = 0

for i in range(1232, 1276):
    firstdrake = "inputs/drake/img_{:04d}.jpg".format(i)
    sketchdrake = "temp/drake/sketch_drake1/img_{:04d}.jpg".format(i)
    cropdrake = "temp/drake/crop_drake/img_{:04d}.jpg".format(i)
    finaldrake = "outputs/drake/drake_comp/img_{:04d}.png".format(i)
    
    countNum += 1
    
    x = 280 + (countNum*2)
    y = 50 + (countNum*3)
    
    command = "convert {}     -colorspace gray     -sigmoidal-contrast 4,0%     -level 20%     -sketch 0x10+160     {}".format(firstdrake, sketchdrake)
    get_ipython().system('{command}')

    command = "convert {} -crop 280x170+{}+{} -bordercolor White -border 10x10 {}".format(firstdrake, x, y, cropdrake)
    get_ipython().system('{command}')

    command = "convert {} {}     -geometry +{}+{}     -composite     -resize 440x330     {}".format(sketchdrake, cropdrake, x, y, finaldrake)
    get_ipython().system('{command}')


# In[5]:

for i in range(1276, 1311):
    firstdrake = "inputs/drake/img_{:04d}.jpg".format(i)
    finaldrake = "outputs/drake/drake_comp/img_{:04d}.png".format(i)

    command = "convert {}     -colorspace gray     -sigmoidal-contrast 9,0%     -level 50%     -fuzz 10%     -edge 2     -negate     -resize 440x330     {}".format(firstdrake, finaldrake)
    get_ipython().system('{command}')


# In[6]:

countNum = 0

for i in range(1311, 1358):
    countNum += 1
    firstdrake = "inputs/drake/img_{:04d}.jpg".format(i)
    finaldrake = "outputs/drake/drake_comp/img_{:04d}.png".format(i)
    
    if countNum <= 2:
        command = "convert {}         -colorspace gray         -sigmoidal-contrast 4,0%         -level 30%         -sketch 0x10+160         -resize 440x330         {}".format(firstdrake, finaldrake)
        get_ipython().system('{command}')
    else:
        command = "convert {}         -colorspace gray         -sigmoidal-contrast 4,0%         -level 10%         -sketch 0x10+160         -negate         -resize 440x330         {}".format(firstdrake, finaldrake)
        get_ipython().system('{command}')
    if countNum == 4:
        countNum = 0


# In[7]:

for i in range(1358, 1401):
    firstdrake = "inputs/drake/img_{:04d}.jpg".format(i)
    finaldrake = "outputs/drake/drake_comp/img_{:04d}.png".format(i)

    command = "convert {}     -colorspace gray     -sigmoidal-contrast 9,0%     -level 50%     -fuzz 10%     -edge 2     -resize 440x330     {}".format(firstdrake, finaldrake)
    get_ipython().system('{command}')


# In[8]:

for i in range(1401, 1442):    
    infile = "inputs/drake/img_{:04d}.jpg".format(i)
    outfile = "temp/drake/drake_sketch/img_{:04d}.jpg".format(i)
    alphafile = "temp/drake/drake_alpha/img_{:04d}.jpg".format(i)
    compfile = "outputs/drake/drake_comp/img_{:04d}.png".format(i)
    command = "convert {}     -colorspace gray     -resize 440x330     -sigmoidal-contrast 3,0%     -level 10%     -sketch 0x10+160       {}".format(infile, outfile)
    get_ipython().system('{command}')
    command2 = "convert {}     -colorspace gray     -resize 440x330     -sigmoidal-contrast 1,0%     -level 50%     -fuzz 10%     -transparent white     -alpha extract       {}".format(infile, alphafile)
    get_ipython().system('{command2}')
    command3 = "convert {} {} {}         -resize 440x330         -composite         {}".format(infile, outfile, alphafile, compfile)
    get_ipython().system('{command3}')


# In[9]:

countNum = 0

for i in range(1442, 1464):
    countNum += 1
    firstdrake = "inputs/drake/img_{:04d}.jpg".format(i)
    finaldrake = "outputs/drake/drake_comp/img_{:04d}.png".format(i)
    
    if countNum <= 2:
        command = "convert {}         -colorspace gray         -sigmoidal-contrast 9,0%         -level 50%         -fuzz 10%         -edge 2         -resize 440x330         {}".format(firstdrake, finaldrake)
        get_ipython().system('{command}')
    else:
        command = "convert {}         -colorspace gray         -sigmoidal-contrast 9,0%         -level 50%         -fuzz 10%         -edge 2         -negate         -resize 440x330         {}".format(firstdrake, finaldrake)
        get_ipython().system('{command}')
    if countNum == 4:
        countNum = 0


# In[10]:

for i in range(1464, 1504):
    
    infile = "inputs/drake/img_{:04d}.jpg".format(i)
    outfile = "temp/drake/drake_sketch/img_{:04d}.jpg".format(i)
    alphafile = "temp/drake/drake_alpha/img_{:04d}.jpg".format(i)
    compfile = "outputs/drake/drake_comp/img_{:04d}.png".format(i)
    command = "convert {}     -colorspace gray     -resize 440x330     -sigmoidal-contrast 3,0%     -level 10%     -sketch 0x10+160       {}".format(infile, outfile)
    get_ipython().system('{command}')
    command2 = "convert {}     -colorspace gray     -resize 440x330     -sigmoidal-contrast 1,0%     -level 25%     -fuzz 10%     -transparent white     -alpha extract       {}".format(infile, alphafile)
    get_ipython().system('{command2}')
    command3 = "convert {} {} {}         -resize 440x330         -composite         {}".format(infile, outfile, alphafile, compfile)
    get_ipython().system('{command3}')


# In[11]:

blend = 1

for i in range(1504, 1610):
    firstdrake = "inputs/drake/img_{:04d}.jpg".format(i)
    finaldrake = "outputs/drake/drake_comp/img_{:04d}.png".format(i)
    darkdrake = "temp/drake/drake_dark/img_{:04d}.jpg".format(i)
    lightdrake = "temp/drake/drake_light/img_{:04d}.jpg".format(i)
    
    command = "convert {}     -colorspace gray     -sigmoidal-contrast 9,0%     -level 20%     -fuzz 10%     -sketch 0x10+160     -resize 440x330     {}".format(firstdrake, lightdrake)
    get_ipython().system('{command}')
    
    command = "convert {}     -colorspace gray     -sigmoidal-contrast 9,0%     -level 20%     -fuzz 10%     -sketch 0x10+160     -negate     -resize 440x330     {}".format(firstdrake, darkdrake)
    get_ipython().system('{command}')
    
    blend_amount = 2*blend
    
    command = "composite     -blend {}     {}     {}     {}".format(blend_amount, lightdrake, darkdrake, finaldrake)
    get_ipython().system('{command}')
    
    blend += 1


# In[12]:

countNum = 0

for i in range(1610, 1696):
    countNum += 1
    firstdrake = "inputs/drake/img_{:04d}.jpg".format(i)
    finaldrake = "outputs/drake/drake_comp/img_{:04d}.png".format(i)
    
    if countNum <= 2:
        command = "convert {}         -colorspace gray         -sigmoidal-contrast 9,0%         -level 50%         -fuzz 10%         -edge 2         -resize 440x330         {}".format(firstdrake, finaldrake)
        get_ipython().system('{command}')
    else:
        command = "convert {}         -resize 440x330         {}".format(firstdrake, finaldrake)
        get_ipython().system('{command}')
    if countNum == 4:
        countNum = 0


# In[13]:

countNum = 0

for i in range(1696, 1721):

    firstdrake = "inputs/drake/img_{:04d}.jpg".format(i)
    sketchcropdrake = "temp/drake/sketchcrop_drake/img_{:04d}.jpg".format(i)
    normdrake = "temp/drake/drake_norm/img_{:04d}.jpg".format(i)
    finaldrake = "outputs/drake/drake_comp/img_{:04d}.png".format(i)
    
    countNum += 1
    
    x = 80 + (countNum*6)
    y = 30 + (countNum*5)
    
    command = "convert {}     -colorspace gray     -sigmoidal-contrast 4,0%     -level 20%     -sketch 0x10+160     -crop 300x220+{}+{} -bordercolor White -border 5x5     {}".format(firstdrake, x, y, sketchcropdrake)
    get_ipython().system('{command}')
    
    command = "convert {}     -resize 440x330     {}".format(firstdrake, normdrake)
    get_ipython().system('{command}')

    command = "convert {} {}     -geometry +{}+{}     -composite     -resize 440x330     {}".format(normdrake, sketchcropdrake, x, y, finaldrake)
    get_ipython().system('{command}')


# In[14]:

for i in range(1721, 1846):
    firstdrake = "inputs/drake/img_{:04d}.jpg".format(i)
    finaldrake = "outputs/drake/drake_comp/img_{:04d}.png".format(i)

    command = "convert {}     -colorspace gray     -sigmoidal-contrast 4,0%     -level 25%     -fuzz 10%     -edge 2     -negate     -resize 440x330     {}".format(firstdrake, finaldrake)
    get_ipython().system('{command}')


# In[15]:

blend = 1

for i in range(1846, 1870):
    firstdrake = "inputs/drake/img_{:04d}.jpg".format(i)
    finaldrake = "outputs/drake/drake_comp/img_{:04d}.png".format(i)
    linedrake = "temp/drake/drake_line/img_{:04d}.jpg".format(i)
    normdrake = "temp/drake/drake_norm/img_{:04d}.jpg".format(i)

    command = "convert {}     -colorspace gray     -sigmoidal-contrast 4,0%     -level 25%     -fuzz 10%     -edge 2     -resize 440x330     {}".format(firstdrake, linedrake)
    get_ipython().system('{command}')
    
    blend_amount = 5*blend
    
    command = "convert {}     -resize 440x330     {}".format(firstdrake, normdrake)
    get_ipython().system('{command}')
    
    command = "composite     -blend {}     {}     {}     {}".format(blend_amount, normdrake, linedrake, finaldrake)
    get_ipython().system('{command}')
    
    blend += 1


# In[16]:

countNum = 0

for i in range(1870, 1948):
    
    infile = "inputs/drake/img_{:04d}.jpg".format(i)
    outfile = "temp/drake/drake_sketch/img_{:04d}.jpg".format(i)
    alphafile = "temp/drake/drake_alpha/img_{:04d}.jpg".format(i)
    compfile = "outputs/drake/drake_comp/img_{:04d}.png".format(i)
    countNum += 1
    
    if countNum <= 2:
        command = "convert {}         -colorspace gray         -resize 440x330         -sigmoidal-contrast 1,0%         -level 20%         -sketch 0x10+160           {}".format(infile, outfile)
        get_ipython().system('{command}')
        command2 = "convert {}         -colorspace gray         -resize 440x330         -sigmoidal-contrast 1,0%         -level 34%         -fuzz 10%         -transparent white         -alpha extract           {}".format(infile, alphafile)
        get_ipython().system('{command2}')
        command3 = "convert {} {} {}             -resize 440x330             -composite             {}".format(infile, outfile, alphafile, compfile)
        get_ipython().system('{command3}')
    
    else:
        command = "convert {}         -resize 440x330         {}".format(infile, compfile)
        get_ipython().system('{command}')
    if countNum == 4:
        countNum = 0


# In[20]:

ffmpeg_sequence = "outputs/drake/drake_comp/img_%4d.png"
movie_file = "movies/drakeseqcomp.mp4"
command = "ffmpeg -start_number 1232 -r 24 -f image2 -i {} -i inputs/aha.mp3 -c:v libx264 -crf 25 -pix_fmt yuv420p -tune fastdecode -y -tune zerolatency -profile:v baseline {}\n".format(ffmpeg_sequence, movie_file)
print(command)
get_ipython().system('{command}')


# In[ ]:



