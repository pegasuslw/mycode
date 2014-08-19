#include "stdio.h"
#include "string.h"

#define _WEB_API_DEBUG_    1

#define LOG_TAG        "---webapi(lw)---"
#define LOG_DEBUG      "---DEBUG---"
#define LOG_INFO       "---INFO---"
#define LOG_ERROR      "---ERROR---"
#define LOG_WARNING    "---WARNING---"
#define LOG_CRITICAL   "---CRITICAL---"
#define LOG_EMERGENCY  "---EMERGENCY---"

#define TV_LOGD(TAG,LOG_LEVEL,format,args...)\
{ \
	if(_WEB_API_DEBUG_){\
		printf("%s%sfile:%s:%s(),line:%d.", TAG,LOG_LEVEL,__FILE__,__FUNCTION__,__LINE__);\
		printf(format, args);\
		if(format[strlen(format)-1] != '\n'){\
		    printf("\n");\
		}\
	}\
}

#define WEB_LOGD(format,args...){\
	TV_LOGD(LOG_TAG, LOG_DEBUG, format, args);\
}

int main(int argc, char* argv[]){
	TV_LOGD("webapi","--DEBUG--","------------------%s----------------%d","hello,world",123);
	WEB_LOGD("------------------%s----------------%d","hello,world",123);
	return 0;
}
