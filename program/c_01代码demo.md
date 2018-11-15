
## C语言布尔bool实现方式-宏

```

#include <stdlib.h>  
#define BOOL int    // C语言 巧妙的使用 宏来解决C语言不支持 BOOL类型的问题   
#define TRUE 1     // C语言 巧妙的使用 宏来解决C语言不支持 TRUE类型的问题   
#define FALSE 0   // C语言 巧妙的使用 宏来解决C语言不支持 FALSE类型的问题   
  
struct array  
{  
    int count;  
    int size;  
    char *pBase;  
};  
void init_arr (struct array *pArr,int number);  
void show_arr (const struct array *pArr);  
BOOL is_empty (const struct array *pArr);  
  
int main (void)  
{  
    struct array arr;  
  
    init_arr (&arr,10);  
    show_arr (&arr);  
  
    return 0;     
}  
void init_arr (struct array *pArr,int number)  
{  
    pArr->pBase = (char *)malloc(sizeof(char)*number);  
    if (NULL == pArr->pBase)  
    {  
        printf ("Memory allocation failed!\a\n");  
        exit(EXIT_FAILURE);  
    }  
    else  
    {  
        pArr->size = number;  
        pArr->count = 0;  
    }  
      
    return;  
}  
void show_arr (const struct array *pArr)  
{  
    int i;  
    if ( is_empty(pArr) )  
        printf ("Array is empty!\a\n");  
    else  
    {  
        for (i=0;i<(pArr->count);i++)  
            printf ("%c ",pArr->pBase[i]);  
        printf ("\n");  
    }  
      
    return;  
}  
BOOL is_empty (const struct array *pArr)  
{  
    if (pArr->count == 0)  
        return TRUE;  
    else  
        return FALSE;  
}  


```


## C语言执行CMD命令

<img src=""/>
<img src="//../zimage/program/c/c_cmd.jpg">
```

//C语言  CMD命令集合
#include <stdio.h>
#include <windows.h>
#include <stdlib.h>
#define MAX_COUNT  18

void show()
{
    int n,z = 1;
    do
    {
        //***********显示提示信息**********
        printf("   ********************************************CMD窗口命令集合*********************************************\n");
        printf("   *                                                                                                       *\n");
        printf("   * 1.appwiz.cpl   [卸载程序]               7.explorer     [文件管理]           13.Netplwiz     [用户账户]*\n");			//显示序号1的功能
        printf("   * 2.compmgmt.msc [计算机管理]             8.Firewall.cpl [防火墙]             14.notepad      [记事本]  *\n");			//显示序号1的功能
        printf("   * 3.comexp.msc   [系统服务]               9.magnify      [放大镜]             15.osk          [屏幕键盘]*\n");			//显示序号1的功能
        printf("   * 4.control      [控制面板]               10.msinfo32    [系统信息]           16.powercfg.cpl [电源选项]*\n");			//显示序号1的功能
        printf("   * 5.devmgmt.msc  [设备管理]               11.mstsc       [远程桌面]           17.regedt32     [注册表]  *\n");			//显示序号1的功能
        printf("   * 6.diskmgmt.msc [磁盘管理]               12.ncpa.cpl    [网络适配器]         18.snippingtool [系统截图]*\n");			//显示序号1的功能
        printf("   *                                                                                                       *\n");
        printf("   *                                           0.退出系统                                                  *\n");			//显示序号0的功能
        printf("   *                                                                                                       *\n");
        printf("   *********************************************************************************************************\n");
        printf("   *                                                                                                       *\n");
        printf("   * 作者：zukgit   制作时间：20xx.xx  制作用途：  use-self                                                *\n");			//显示关于作者、制作时间以及制作用途的信息
        printf("   *                                                                                                       *\n");
        printf("   *********************************************************************************************************\n");
        
        //********************************
        
        printf("   请输入功能序号: ");				//提示用户输入
        
        while(!scanf("%d",&n))					    //接受用户输入的功能序号，并且进行排错
        {
            printf("   输入序号有误,请重新输入: ");
            fflush(stdin);
        }
        printf("   输您输入的是：%d\n",n);
        
        while((n < 0 || n > MAX_COUNT))										//对用户输入不能实现的功能序号进行处理
        {
            printf("   抱歉,没有此功能,请重新输入功能序号: ");		//提示用户所输入的功能序号系统不能进行处理
            fflush(stdin);
            while(!scanf("%d",&n))									//接收用户重新输入的功能序号
            {
                printf("   输入序号有误,请重新输入: ");
                fflush(stdin);
            }
            printf("\n");											//输出回车
        }
        
        switch(n)							//对用户信息进行功能函数的调用
        {
            case 0:
            {
                printf("您将在3秒后退出");
                Sleep(3000);
                z = 0;
                break;
            }
            case 1:
            {
                system("appwiz.cpl");
                break;
            }
            case 2:
            {
                system("compmgmt.msc");
                break;
            }
            case 3:
            {
                system("comexp.msc");
                break;
            }
            case 4:
            {
                system("control");
                break;
            }
            case 5:
            {
                system("devmgmt.msc");
                break;
            }
            case 6:
            {
                system("diskmgmt.msc");
                break;
            }
            case 7:
            {
                system("explorer");
                break;
            }
            case 8:
            {
                system("Firewall.cpl");
                break;
            }
            case 9:
            {
                system("magnify");
                break;
            }
            case 10:
            {
                system("msinfo32");
                break;
            }
            case 11:
            {
                system("mstsc");
                break;
            }
            case 12:
            {
                system("ncpa.cpl");
                break;
            }
            case 13:
            {
                system("Netplwiz");
                break;
            }
            case 14:
            {
                system("notepad");
                break;
            }
            case 15:
            {
                system("osk");
                break;
            }
            case 16:
            {
                system("powercfg.cpl");
                break;
            }
            case 17:
            {
                system("regedt32");
                break;
            }	
            case 18:
            {
                system("snippingtool");
                break;
            }																																																							
        }
        
    }while(z == 1);								//输出回车
    
}

int main()  
{
    show();		//调用显示功能函数
    return 0;
}


```