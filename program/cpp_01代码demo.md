

# C++_时钟demo

```

//C++语言  时钟  
#include <stdio.h>  
#include <windows.h>   
  
int main()  
{  
  
    SYSTEMTIME time1;   
  
    while(1)  
    {  
    GetLocalTime(&time1);  
  
    printf("\t\t**********************************************");  
    printf("\n\n\n\t\t\t当前时间：%d 时 %d 分 %d 秒\n\n\n",time1.wHour,time1.wMinute,time1.wSecond);  
    printf("\t\t**********************************************");  
    Sleep(1000);  
    system("cls");  
    }  
    getchar();  
    return 0;   
  
} 



```