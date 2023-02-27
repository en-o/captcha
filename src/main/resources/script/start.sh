#!/bin/bash


# PROJECT_PATH=/doc/program/smart/soul-admin.jar
# read -p "：" PROJECT_PATH
# echo "你输入的数是：$PROJECT_PATH"
# export JAVA_HOME=/usr/local/java
# export PATH=$JAVA_HOME/bin:$PATH
# export CLASSPATH=.:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar
PROJECT_PATH=captcha-0.0.1-SNAPSHOT.jar
while getopts f:p:v:j: OPT; do #选项后面的冒号表示该选项需要参数
  case ${OPT} in
    f) PROJECT_PROPERTIES=${OPTARG} #配置文件
       ;;
    p) PROJECT_PORT=${OPTARG} #端口号
       ;;
    \?)
       printf "[Usage] `basename $0` -f <配置文件> -p <端口号>\n" >&2
       exit 1
  esac
done

# 设置环境参数
JAVA_OPTS=" -Xms512m "
# -XX:+UseConcMarkSweepGC -XX:+UseParNewGC || -XX:+UseParallelGC -XX:+UseParallelOldGC
JAVA_OPTS="${JAVA_OPTS} -Xmx1024m -Xmn1024m -XX:+UseParallelGC -XX:+UseParallelOldGC "
JAVA_OPTS="${JAVA_OPTS} -Dfile.encoding=UTF-8"

# 设置默认值
if [ ! $PROJECT_PROPERTIES ]; then
	SERVER_PROPERTIES="--spring.profiles.active=prod"
else
	SERVER_PROPERTIES="--spring.profiles.active=${PROJECT_PROPERTIES}"
fi

if [ ! $PROJECT_PORT ]; then
	SERVER_PORT=" --server.port=9003"
else
	SERVER_PORT=" --server.port=${PROJECT_PORT}"
fi



# shutdown
pid=`ps -ef|grep $PROJECT_PATH | grep -v grep | awk '{print $2}'`
kill -9 $pid
echo "$pid进程终止成功"
sleep 1

# startUp
sleep 2
# 判断jar包文件是否存在，如果存在启动jar包，并时时查看启动日志
if test -e $PROJECT_PATH
then
echo "$PROJECT_PATH 文件存在,开始启动此程序..."

# 启动jar包，指向日志文件，2>&1 & 表示打开或指向同一个日志文件
echo $JAVA_OPTS $PROJECT_PATH $SERVER_PORT $SERVER_PROPERTIES
nohup java -jar $JAVA_OPTS $PROJECT_PATH $SERVER_PORT $SERVER_PROPERTIES  >/dev/null 2>&1 &

echo "$PROJECT_PATH  启动成功..."

else
echo "$PROJECT_PATH  文件不存在,请检查。"
fi
