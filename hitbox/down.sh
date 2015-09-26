curl http://jadisco.pl/archiwum.php > arch.html

for a in `cat arch.html | grep hb | cut -d'=' -f4 | cut -d'"' -f1`; 
do 

if [ -f ${a}.mp4 ]
then
echo $a already fetched!, `stat $a.mp4`
else
livestreamer http://www.hitbox.tv/video/$a best -o $a.mp4 ; 
fi

done;

