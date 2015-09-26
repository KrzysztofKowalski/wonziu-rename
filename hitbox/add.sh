
for a in `cat add.html | grep '/video/'|grep 'ng-binding'|cut -d'=' -f4|cut -d '/' -f3|cut -d'"' -f1`; 
do 

R=`ls $a* | wc -l`

if [ $R -gt 0 ]
then
echo $a already fetched!, `stat $a.mp4`
else
livestreamer http://www.hitbox.tv/video/$a best -o $a.mp4 ; 
fi

done;

