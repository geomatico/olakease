if [ -z $OLAKEASE_CONFIG ]
	then OLAKEASE_CONFIG=$HOME/.olakease/conf
fi

if [ ! -f $OLAKEASE_CONFIG ]
	then echo The configuration file does not exist: $OLAKEASE_CONFIG
	exit 1
fi

database=`grep database $OLAKEASE_CONFIG | awk '{print $2}'`
developer_name=`grep developer_name $OLAKEASE_CONFIG | awk '{print $2}'`
pw=`grep password $OLAKEASE_CONFIG | awk '{print $2}'`
