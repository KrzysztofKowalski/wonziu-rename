for a in `cat twitch.html |grep 'twitch'|grep cap|cut -d'=' -f4|cut -d'"' -f2|cut -d'/' -f6`
do

if [ -f ${a}.mp4 ]
then
echo $a already fetched!, `stat $a.mp4`
else
livestreamer http://www.twitch.tv/wonziu/c/$a best -o $a.mp4 ; 
fi

done;