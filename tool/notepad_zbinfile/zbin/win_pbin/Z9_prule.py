#!/usr/bin/python
# -*- coding: UTF-8 -*-

import random
import pygame
import sys
import os
from enum import Enum
import time
import traceback
from pygame.locals import *
from random import randint
from random import seed
import  math
#  全局变量  与系统 息息相关的 项 
class OS_TYPE(Enum):
    Windows = "windows"
    Linux = "linux"
    MacOS = "macos"
    Android = "android"
    IOS = "ios"


CUR_Batch_End = ".bat"
CUR_Bat_Name_Notype = "prule_apply_Z9"
CUR_Bat_Name = "prule_apply_Z9"
CUR_OS_TYPE = OS_TYPE.Windows
CUR_OS_ExeTYPE = ""
CUR_Shell_PATH = ""
CUR_Rule_Index = "-1"
zbinPath = ""
desktopPath = ""
pbinPath = ""
prulePath = ""
params_list=list()
realTypeRuleList=list()
now_yyyymmdd=str(time.strftime('%Y%m%d', time.localtime(time.time())))
Device_Height = 1080
Device_Width = 720


def GetDesktopPath():
    return os.path.join(os.path.expanduser("~"), 'Desktop')





def initSystemInfo():
    print("════════════════"+"System Info Begin"+"════════════════")
    global CUR_Rule_Index 
    global desktopPath
    global zbinPath
    global pbinPath
    global prulePath
    global CUR_Bat_Name
    global CUR_Shell_PATH
    global CUR_Batch_End
    global CUR_OS_TYPE
    global CUR_OS_ExeTYPE
    global params_list
    global Device_Height
    global Device_Width  
    
    pygame.init()
    screenInfo = pygame.display.Info()
    Device_Width = screenInfo.current_w
    Device_Height = screenInfo.current_h
    desktopPath = GetDesktopPath()
    zbinPath=str(desktopPath)+os.sep+"zbin"
    pbinPath=zbinPath+os.sep+"win_pbin"
    prulePath=pbinPath+os.sep+CUR_Bat_Name_Notype+CUR_Batch_End
    CUR_Bat_Name=CUR_Bat_Name_Notype+CUR_Batch_End

    if len(sys.argv) >= 2:
        CUR_Shell_PATH=sys.argv[1]

    if len(sys.argv) >= 3:
        CUR_Rule_Index=sys.argv[2]
        CUR_Rule_Index=CUR_Rule_Index.replace("#", "").replace("_", "")

    if len(sys.argv) >= 4:
        params_list=sys.argv[3:]


    print("CUR_OS_TYPE:"+str(CUR_OS_TYPE))
    print("输入参数个数:"+str(len(sys.argv)))
    print("参数列表:"+str(sys.argv))
    print("操作系统:"+str(sys.platform))
    print("屏幕宽度_Width :"+str(Device_Width))
    print("屏幕宽度_Height:"+str(Device_Height))
    print("批处理后缀:"+CUR_Batch_End)
    print("批处理文件名称:"+CUR_Bat_Name)
    print("desktopPath:"+str(desktopPath))
    print("zbinPath:"+str(zbinPath));
    print("pbinPath:"+str(pbinPath)); 
    print("prulePath:"+str(prulePath));
    print("python可执行文件路径:"+str(sys.executable))
    print("now_yyyymmdd:"+str(now_yyyymmdd))
    print("CUR_Shell_PATH 执行命令路径:"+str(CUR_Shell_PATH))
    print("CUR_Rule_Index 当前所中规则:"+str(CUR_Rule_Index))
    print("params_list 规则参数列表:"+str(params_list))

    print("════════════════"+"System Info End"+"════════════════")





##// operation_type 
## 操作类型 0--不读取文件 不依靠参数 实现 自身逻辑
## 操作类型 1--读取文件内容字符串  进行修改
## 操作类型 2--对文件对文件内容(字节)--进行修改 
## 操作类型 3--对全体子文件进行的随性的操作 // 属性进行修改(文件名称)
## 操作类型4--对当前子文件(包括子目录 子文件 --不包含孙目录 孙文件) // 
## 操作类型 5--从shell 中获取到的路径 去对某一个文件进行操作



class Rule:
    '基础规则类'
    rule_index = 0
    operation_type = 0
    file_type = 0
    os_battype=".bat"  ## .sh


    def __init__(self, rule_index, operation_type):
        self.rule_index = rule_index
        self.operation_type = operation_type

    def __init__(self, rule_index, operation_type,file_type):
        self.rule_index = rule_index
        self.operation_type = operation_type
        self.file_type = file_type

# abstract String applyNoParamOperationRule0( );
    def applyNoParamOperationRule0(self):
        print("applyNoParamOperationRule0")

# abstract String applyStringOperationRule1(String origin);
    def applyStringOperationRule1(self,rawContent):
        return ""

# abstract File applyFileByteOperationRule2(File originFile);
    def applyFileByteOperationRule2(self,rawfile):
        return None

# abstract ArrayList<File> applyFileListRule3(ArrayList<File> subFileList,HashMap<String, ArrayList<File>> fileTypeMap);     
    def applyFileListRule3(self,subFileList,fileType_file_Map):
        return None

# abstract ArrayList<File> applySubFileListRule4(ArrayList<File> curFileList,HashMap<String, ArrayList<File>> subFileTypeMap,
# ArrayList<File> curDirList,ArrayList<File> curRealFileList);

    def applySubFileListRule4(self,subFileList,fileType_file_Map , alldirlist , allrealfilelist):
        return None


    def allowEmptyDirFileList(self):
        return False

    def initParamsWithInputList(self,inputParamList):
        return True


    def simpleDesc(self):
        return ""

    def ruleTip(self):
        simpdescStr =  self.simpleDesc();
        return CUR_Bat_Name + "  "  + "#_" + str(self.rule_index) + "   ## [索引 " + str(self.rule_index) + "]  描述:" + str(simpdescStr)


class Basic_Rule(Rule):   
    '公共基础类'
    rule_index = 0
    operation_type = 0
    file_type = 0
    os_battype=".bat"  ## .sh


    def __init__(self, rule_index, operation_type):
        self.rule_index = rule_index
        self.operation_type = operation_type

    def __init__(self, rule_index, operation_type,file_type):
        self.rule_index = rule_index
        self.operation_type = operation_type
        self.file_type = file_type

###################### Rule_1 Begin  ######################
class CodeRain_Rule_1(Basic_Rule):

    def __init__(self, rule_index, operation_type):
        self.rule_index = rule_index
        self.operation_type = operation_type

#    def __init__(self, rule_index, operation_type,file_type):
#        self.rule_index = rule_index
#        self.operation_type = operation_type
#        self.file_type = file_type


    def applyNoParamOperationRule0(self):
        pygame.init()
        screenInfo = pygame.display.Info()
        PANEL_width = screenInfo.current_w
        PANEL_highly = screenInfo.current_h
        FONT_PX = 15
        winSur = pygame.display.set_mode((PANEL_width, PANEL_highly))  # 全屏模式
        # global Device_Width
        # global Device_Height
        # winSur = pygame.display.set_mode((Device_Width, Device_Height))  # 全屏模式
        font = pygame.font.SysFont("arial", 20)
        bg_suface = pygame.Surface((PANEL_width, PANEL_highly), flags=pygame.SRCALPHA)
        pygame.Surface.convert(bg_suface)
        bg_suface.fill(pygame.Color(0, 0, 0, 28))
        winSur.fill((0, 0, 0))
        letter = ["0", "1"]
        texts = [font.render(str(letter[i]), True, (0, 255, 0))
                for i in range(len(letter))]
        column = int(PANEL_width / FONT_PX)
        drops = [0 for i in range(column)]
        while True:
            for event in pygame.event.get():
                if event.type == pygame.KEYDOWN:  # 按esc键退出
                    pygame.quit()
            pygame.time.delay(30)
            winSur.blit(bg_suface, (0, 0))
            for i in range(len(drops)):
                text = random.choice(texts)
                winSur.blit(text, (i * FONT_PX, drops[i] * FONT_PX))
                drops[i] += 1
                if drops[i] * 10 > PANEL_highly or random.random() > 0.95:
                    drops[i] = 0
            pygame.display.flip()

    def simpleDesc(self):
        return "屏幕打印二进制代码雨"

###################### Rule_1 End  ######################



###################### Rule_2 Begin  ######################
# ═════════════════════════ Rule_2 Pre Define
rule1_bg_size = rule1_width, rule1_height = 1080, 720
#rule1_bg_size = rule1_width, rule1_height = 1920, 1080
#rule1_bg_size = rule1_width, rule1_width = 480, 700
rule1_screen = ""

rule1_BLACK = (0, 0, 0)
rule1_WHITE = (255, 255, 255)
rule1_GREEN = (0, 255, 0)
rule1_RED = (255, 0, 0) 

rule1_background         = ""
rule1_bullet_sound       = ""
rule1_bomb_sound         = ""
rule1_supply_sound       = ""
get_rule1_bomb_sound     = ""
get_rule1_bullet_sound   = ""
rule1_upgrade_sound      = ""
rule1_enemy3_fly_sound   = ""
rule1_enemy1_down_sound  = ""
rule1_enemy2_down_sound  = ""
rule1_enemy3_down_sound  = ""
rule1_me_down_sound      = ""

  





def add_small_enemies(group1, group2, num):
    for i in range(num):
        e1 = SmallEnemy(rule1_bg_size)
        group1.add(e1)
        group2.add(e1)


def add_mid_enemies(group1, group2, num):
    for i in range(num):
        e2 = MidEnemy(rule1_bg_size)
        group1.add(e2)
        group2.add(e2)


def add_big_enemies(group1, group2, num):
    for i in range(num):
        e3 = BigEnemy(rule1_bg_size)
        group1.add(e3)
        group2.add(e3)


def inc_speed(target, inc):
    for each in target:
        each.speed += inc


def rule_1_main():
    pygame.init()
    pygame.mixer.music.play(-1)
    
    # 生成我方飞机
    me = MyPlane(rule1_bg_size)
    
    enemies = pygame.sprite.Group()
    
    # 生成敌方小型飞机 x10
    small_enemies = pygame.sprite.Group()
    add_small_enemies(small_enemies, enemies, 150)
    
    # 生成敌方中型飞机 x10
    mid_enemies = pygame.sprite.Group()
    add_mid_enemies(mid_enemies, enemies, 40)
    
    # 生成敌方大型飞机 x10
    big_enemies = pygame.sprite.Group()
    add_big_enemies(big_enemies, enemies, 20)
    
    # 生成普通子弹
    bullet1 = []
    bullet1_index = 0
    # 决定了 子弹的 高度 
    BULLET1_NUM = 8
    for i in range(BULLET1_NUM):
        bullet1.append(Bullet1(me.rect.midtop))


    
    # 生成超级子弹
    bullet2 = []
    bullet2_index = 0
    # 决定了 超级子弹的 高度
    BULLET2_NUM = 16
    for i in range(BULLET2_NUM // 2):
        bullet2.append(Bullet2((me.rect.centerx - 33, me.rect.centery)))
        bullet2.append(Bullet2((me.rect.centerx + 30, me.rect.centery)))
    
    clock = pygame.time.Clock()
    
    # 中弹图片索引
    e1_destroy_index = 0
    e2_destroy_index = 0
    e3_destroy_index = 0
    me_destroy_index = 0
    
    # 统计得分
    score = 0
    
    # 设置难度级别
    level = 1
    
    score_font = pygame.font.Font(pbinPath+"\\Z9\\rule1_font\\font.ttf", 36)
    
    # 标志是否暂停游戏
    paused = False
    pause_nor_image = pygame.image.load(pbinPath+"\\Z9\\rule1_images\\pause_nor.png").convert_alpha()
    pause_pressed_image = pygame.image.load(pbinPath+"\\Z9\\rule1_images\\pause_pressed.png").convert_alpha()
    resume_nor_image = pygame.image.load(pbinPath+"\\Z9\\rule1_images\\resume_nor.png").convert_alpha()
    resume_pressed_image = pygame.image.load(pbinPath+"\\Z9\\rule1_images\\resume_pressed.png").convert_alpha()
    paused_rect = pause_nor_image.get_rect()
    paused_rect.left, paused_rect.top = rule1_width - paused_rect.width - 10, 10   # 暂停按钮设置  480 , 720
    # paused_rect.left, paused_rect.top = rule1_width - paused_rect.width + 150, 10    # 暂停按钮设置 1080 720 
    paused_image = pause_nor_image
    

    
    # 全屏炸弹
    bomb_image = pygame.image.load(pbinPath+"\\Z9\\rule1_images\\bomb.png").convert_alpha()
    bomb_rect = bomb_image.get_rect()
    bomb_font = pygame.font.Font(pbinPath+"\\Z9\\rule1_font\\font.ttf", 48)
    # 超级子弹数量
    bomb_num = 300
    
    # 每10秒发放一个补给包
    bullet_supply = Bullet_Supply(rule1_bg_size)
    bomb_supply = Bomb_Supply(rule1_bg_size)
    SUPPLY_TIME = USEREVENT
    pygame.time.set_timer(SUPPLY_TIME, 10 * 1000)
    
    # 超级子弹定时器
    DOUBLE_BULLET_TIME = USEREVENT + 1
    
    # 标志是否使用超级子弹
    is_double_bullet = False
    
    # 解除我方无敌状态定时器
    INVINCIBLE_TIME = USEREVENT + 2
    
    # 生命数量
    life_image = pygame.image.load(pbinPath+"\\Z9\\rule1_images\\life.png").convert_alpha()
    life_rect = life_image.get_rect()
    life_num = 3
    
    # 用于阻止重复打开记录文件
    recorded = False
    
    # 游戏结束画面
    gameover_font = pygame.font.Font(pbinPath+"\\Z9\\rule1_font\\font.TTF", 48)
    again_image = pygame.image.load(pbinPath+"\\Z9\\rule1_images\\again.png").convert_alpha()
    again_rect = again_image.get_rect()
    gameover_image = pygame.image.load(pbinPath+"\\Z9\\rule1_images\\gameover.png").convert_alpha()
    gameover_rect = gameover_image.get_rect()
    
    # 用于切换图片
    switch_image = True
    
    # 用于延迟
    delay = 100
    
    running = True
    
    while running:
        for event in pygame.event.get():
            if event.type == QUIT:
                pygame.quit()
                sys.exit()
            
            elif event.type == MOUSEBUTTONDOWN:
                if event.button == 1 and paused_rect.collidepoint(event.pos):
                    paused = not paused
                    if paused:
                        pygame.time.set_timer(SUPPLY_TIME, 0)
                        pygame.mixer.music.pause()
                        pygame.mixer.pause()
                    else:
                        pygame.time.set_timer(SUPPLY_TIME, 10 * 1000)
                        pygame.mixer.music.unpause()
                        pygame.mixer.unpause()
            
            elif event.type == MOUSEMOTION:
                if paused_rect.collidepoint(event.pos):
                    if paused:
                        paused_image = resume_pressed_image
                    else:
                        paused_image = pause_pressed_image
                else:
                    if paused:
                        paused_image = resume_nor_image
                    else:
                        paused_image = pause_nor_image
            
            elif event.type == KEYDOWN:
                if event.key == K_SPACE:
                    if bomb_num:
                        bomb_num -= 1
                        rule1_bomb_sound.play()
                        for each in enemies:
                            if each.rect.bottom > 0:
                                each.active = False
            
            elif event.type == SUPPLY_TIME:
                rule1_supply_sound.play()
                if random.choice([True, False]):
                    bomb_supply.reset()
                else:
                    bullet_supply.reset()
            
            elif event.type == DOUBLE_BULLET_TIME:
                is_double_bullet = False
                pygame.time.set_timer(DOUBLE_BULLET_TIME, 0)
            
            elif event.type == INVINCIBLE_TIME:
                me.invincible = False
                pygame.time.set_timer(INVINCIBLE_TIME, 0)
        
        # 根据用户的得分增加难度
        if level == 1 and score > 50000:
            level = 2
            rule1_upgrade_sound.play()
            # 增加3架小型敌机、2架中型敌机和1架大型敌机
            add_small_enemies(small_enemies, enemies, 3)
            add_mid_enemies(mid_enemies, enemies, 2)
            add_big_enemies(big_enemies, enemies, 1)
            # 提升小型敌机的速度
            inc_speed(small_enemies, 1)
        elif level == 2 and score > 300000:
            level = 3
            rule1_upgrade_sound.play()
            # 增加5架小型敌机、3架中型敌机和2架大型敌机
            add_small_enemies(small_enemies, enemies, 5)
            add_mid_enemies(mid_enemies, enemies, 3)
            add_big_enemies(big_enemies, enemies, 2)
            # 提升小型敌机的速度
            inc_speed(small_enemies, 1)
            inc_speed(mid_enemies, 1)
        elif level == 3 and score > 600000:
            level = 4
            rule1_upgrade_sound.play()
            # 增加5架小型敌机、3架中型敌机和2架大型敌机
            add_small_enemies(small_enemies, enemies, 5)
            add_mid_enemies(mid_enemies, enemies, 3)
            add_big_enemies(big_enemies, enemies, 2)
            # 提升小型敌机的速度
            inc_speed(small_enemies, 1)
            inc_speed(mid_enemies, 1)
        elif level == 4 and score > 1000000:
            level = 5
            rule1_upgrade_sound.play()
            # 增加5架小型敌机、3架中型敌机和2架大型敌机
            add_small_enemies(small_enemies, enemies, 5)
            add_mid_enemies(mid_enemies, enemies, 3)
            add_big_enemies(big_enemies, enemies, 2)
            # 提升小型敌机的速度
            inc_speed(small_enemies, 1)
            inc_speed(mid_enemies, 1)
        
        rule1_screen.blit(rule1_background, (0, 0))
        
        if life_num and not paused:
            # 检测用户的键盘操作
            key_pressed = pygame.key.get_pressed()
            
            if key_pressed[K_w] or key_pressed[K_UP]:
                me.moveUp()
            if key_pressed[K_s] or key_pressed[K_DOWN]:
                me.moveDown()
            if key_pressed[K_a] or key_pressed[K_LEFT]:
                me.moveLeft()
            if key_pressed[K_d] or key_pressed[K_RIGHT]:
                me.moveRight()
            
            # 绘制全屏炸弹补给并检测是否获得
            if bomb_supply.active:
                bomb_supply.move()
                rule1_screen.blit(bomb_supply.image, bomb_supply.rect)
                if pygame.sprite.collide_mask(bomb_supply, me):
                    get_rule1_bomb_sound.play()
                    if bomb_num < 3:
                        bomb_num += 1
                    bomb_supply.active = False
            
            # 绘制超级子弹补给并检测是否获得
            if bullet_supply.active:
                bullet_supply.move()
                rule1_screen.blit(bullet_supply.image, bullet_supply.rect)
                if pygame.sprite.collide_mask(bullet_supply, me):
                    get_rule1_bullet_sound.play()
                    is_double_bullet = True
                    pygame.time.set_timer(DOUBLE_BULLET_TIME, 18 * 1000)
                    bullet_supply.active = False
            
            # 发射子弹
            if not (delay % 10):
                rule1_bullet_sound.play()
                if is_double_bullet:
                    bullets = bullet2
                    bullets[bullet2_index].reset((me.rect.centerx - 33, me.rect.centery))
                    bullets[bullet2_index + 1].reset((me.rect.centerx + 30, me.rect.centery))
                    bullet2_index = (bullet2_index + 2) % BULLET2_NUM
                else:
                    bullets = bullet1
                    bullets[bullet1_index].reset(me.rect.midtop)
                    #bullets[bullet1_index].reset(rule1_bg_size[1])

                    bullet1_index = (bullet1_index + 1) % BULLET1_NUM
            
            # 检测子弹是否击中敌机
            for b in bullets:
                if b.active:
                    b.move()
                    rule1_screen.blit(b.image, b.rect)
                    enemy_hit = pygame.sprite.spritecollide(b, enemies, False, pygame.sprite.collide_mask)
                    if enemy_hit:
                        b.active = False
                        for e in enemy_hit:
                            if e in mid_enemies or e in big_enemies:
                                e.hit = True
                                e.energy -= 1
                                if e.energy == 0:
                                    e.active = False
                            else:
                                e.active = False
            
            # 绘制大型敌机
            for each in big_enemies:
                if each.active:
                    each.move()
                    if each.hit:
                        rule1_screen.blit(each.image_hit, each.rect)
                        each.hit = False
                    else:
                        if switch_image:
                            rule1_screen.blit(each.image1, each.rect)
                        else:
                            rule1_screen.blit(each.image2, each.rect)
                    
                    # 绘制血槽
                    pygame.draw.line(rule1_screen, rule1_BLACK, \
                                     (each.rect.left, each.rect.top - 5), \
                                     (each.rect.right, each.rect.top - 5), \
                                     2)
                    # 当生命大于20%显示绿色，否则显示红色
                    energy_remain = each.energy / BigEnemy.energy
                    if energy_remain > 0.2:
                        energy_color = rule1_GREEN
                    else:
                        energy_color = rule1_RED
                    pygame.draw.line(rule1_screen, energy_color, \
                                     (each.rect.left, each.rect.top - 5), \
                                     (each.rect.left + each.rect.width * energy_remain, \
                                      each.rect.top - 5), 2)
                    
                    # 即将出现在画面中，播放音效
                    if each.rect.bottom == -50:
                        rule1_enemy3_fly_sound.play(-1)
                else:
                    # 毁灭
                    if not (delay % 3):
                        if e3_destroy_index == 0:
                            rule1_enemy3_down_sound.play()
                        rule1_screen.blit(each.destroy_images[e3_destroy_index], each.rect)
                        e3_destroy_index = (e3_destroy_index + 1) % 6
                        if e3_destroy_index == 0:
                            rule1_enemy3_fly_sound.stop()
                            score += 10000
                            each.reset()
            
            # 绘制中型敌机：
            for each in mid_enemies:
                if each.active:
                    each.move()
                    
                    if each.hit:
                        rule1_screen.blit(each.image_hit, each.rect)
                        each.hit = False
                    else:
                        rule1_screen.blit(each.image, each.rect)
                    
                    # 绘制血槽
                    pygame.draw.line(rule1_screen, rule1_BLACK, \
                                     (each.rect.left, each.rect.top - 5), \
                                     (each.rect.right, each.rect.top - 5), \
                                     2)
                    # 当生命大于20%显示绿色，否则显示红色
                    energy_remain = each.energy / MidEnemy.energy
                    if energy_remain > 0.2:
                        energy_color = rule1_GREEN
                    else:
                        energy_color = rule1_RED
                    pygame.draw.line(rule1_screen, energy_color, \
                                     (each.rect.left, each.rect.top - 5), \
                                     (each.rect.left + each.rect.width * energy_remain, \
                                      each.rect.top - 5), 2)
                else:
                    # 毁灭
                    if not (delay % 3):
                        if e2_destroy_index == 0:
                            rule1_enemy2_down_sound.play()
                        rule1_screen.blit(each.destroy_images[e2_destroy_index], each.rect)
                        e2_destroy_index = (e2_destroy_index + 1) % 4
                        if e2_destroy_index == 0:
                            score += 6000
                            each.reset()
            
            # 绘制小型敌机：
            for each in small_enemies:
                if each.active:
                    each.move()
                    rule1_screen.blit(each.image, each.rect)
                else:
                    # 毁灭
                    if not (delay % 3):
                        if e1_destroy_index == 0:
                            rule1_enemy1_down_sound.play()
                        rule1_screen.blit(each.destroy_images[e1_destroy_index], each.rect)
                        e1_destroy_index = (e1_destroy_index + 1) % 4
                        if e1_destroy_index == 0:
                            score += 1000
                            each.reset()
            
            # 检测我方飞机是否被撞
            enemies_down = pygame.sprite.spritecollide(me, enemies, False, pygame.sprite.collide_mask)
            if enemies_down and not me.invincible:
                me.active = False
                for e in enemies_down:
                    e.active = False
            
            # 绘制我方飞机
            if me.active:
                if switch_image:
                    rule1_screen.blit(me.image1, me.rect)
                else:
                    rule1_screen.blit(me.image2, me.rect)
            else:
                # 毁灭
                if not (delay % 3):
                    if me_destroy_index == 0:
                        rule1_me_down_sound.play()
                    rule1_screen.blit(me.destroy_images[me_destroy_index], me.rect)
                    me_destroy_index = (me_destroy_index + 1) % 4
                    if me_destroy_index == 0:
                        life_num -= 1
                        me.reset()
                        pygame.time.set_timer(INVINCIBLE_TIME, 3 * 1000)
            
            # 绘制全屏炸弹数量
            bomb_text = bomb_font.render("× %d" % bomb_num, True, rule1_WHITE)
            text_rect = bomb_text.get_rect()
            rule1_screen.blit(bomb_image, (10, rule1_height - 10 - bomb_rect.height))
            rule1_screen.blit(bomb_text, (20 + bomb_rect.width, rule1_height - 5 - text_rect.height))
            
            # 绘制剩余生命数量
            if life_num:
                for i in range(life_num):
                    # 480-720 绘制 生命值
                    rule1_screen.blit(life_image, \
                                (rule1_width - 10 - (i + 1) * life_rect.width, \
                                 rule1_height - 10 - life_rect.height))
          
            
            # 绘制得分
            score_text = score_font.render("Score : %s" % str(score), True, rule1_WHITE)
            rule1_screen.blit(score_text, (10, 5))
        
        # 绘制游戏结束画面
        elif life_num == 0:
            # 背景音乐停止
            pygame.mixer.music.stop()
            
            # 停止全部音效
            pygame.mixer.stop()
            
            # 停止发放补给
            pygame.time.set_timer(SUPPLY_TIME, 0)
            
            if not recorded:
                recorded = True
                # 读取历史最高得分
                #with open("record.txt", "r") as f:
                with open(pbinPath+"\\Z9\\rule1_font\\record.txt", "r") as f:
                    record_score = int(f.read())
                
                # 如果玩家得分高于历史最高得分，则存档
                if score > record_score:
                    #with open("record.txt", "w") as f:
                    with open(pbinPath+"\\Z9\\rule1_font\\record.txt", "w") as f:
                        f.write(str(score))
            
            # 绘制结束画面
            record_score_text = score_font.render("Best : %d" % record_score, True, (255, 255, 255))
            rule1_screen.blit(record_score_text, (50, 50))
            
            gameover_text1 = gameover_font.render("Your Score", True, (255, 255, 255))
            gameover_text1_rect = gameover_text1.get_rect()
            gameover_text1_rect.left, gameover_text1_rect.top = \
                (rule1_width - gameover_text1_rect.width) // 2, rule1_height // 3
            rule1_screen.blit(gameover_text1, gameover_text1_rect)
            
            gameover_text2 = gameover_font.render(str(score), True, (255, 255, 255))
            gameover_text2_rect = gameover_text2.get_rect()
            gameover_text2_rect.left, gameover_text2_rect.top = \
                (rule1_width - gameover_text2_rect.width) // 2, \
                gameover_text1_rect.bottom + 10
            rule1_screen.blit(gameover_text2, gameover_text2_rect)
            
            again_rect.left, again_rect.top = \
                (rule1_width - again_rect.width) // 2, \
                gameover_text2_rect.bottom + 50
            rule1_screen.blit(again_image, again_rect)
            
            gameover_rect.left, gameover_rect.top = \
                (rule1_width - again_rect.width) // 2, \
                again_rect.bottom + 10
            rule1_screen.blit(gameover_image, gameover_rect)
            
            # 检测用户的鼠标操作
            # 如果用户按下鼠标左键
            if pygame.mouse.get_pressed()[0]:
                # 获取鼠标坐标
                pos = pygame.mouse.get_pos()
                # 如果用户点击“重新开始”
                if again_rect.left < pos[0] < again_rect.right and \
                        again_rect.top < pos[1] < again_rect.bottom:
                    # 调用main函数，重新开始游戏
                    rule_1_main()
                # 如果用户点击“结束游戏”
                elif gameover_rect.left < pos[0] < gameover_rect.right and \
                        gameover_rect.top < pos[1] < gameover_rect.bottom:
                    # 退出游戏
                    pygame.quit()
                    sys.exit()
                    
                    # 绘制暂停按钮
        rule1_screen.blit(paused_image, paused_rect)
        
        # 切换图片
        if not (delay % 5):
            switch_image = not switch_image
        
        delay -= 1
        if not delay:
            delay = 100
        
        pygame.display.flip()
        clock.tick(60)


class MyPlane(pygame.sprite.Sprite):
    def __init__(self, rule1_bg_size):
        pygame.sprite.Sprite.__init__(self)

        self.image1 = pygame.image.load(pbinPath+"\\Z9\\rule1_images\\me1.png").convert_alpha()
        self.image2 = pygame.image.load(pbinPath+"\\Z9\\rule1_images\\me2.png").convert_alpha()
        self.destroy_images = []
        self.destroy_images.extend([\
            pygame.image.load(pbinPath+"\\Z9\\rule1_images\\me_destroy_1.png").convert_alpha(), \
            pygame.image.load(pbinPath+"\\Z9\\rule1_images\\me_destroy_2.png").convert_alpha(), \
            pygame.image.load(pbinPath+"\\Z9\\rule1_images\\me_destroy_3.png").convert_alpha(), \
            pygame.image.load(pbinPath+"\\Z9\\rule1_images\\me_destroy_4.png").convert_alpha() \
            ])
        self.rect = self.image1.get_rect()
        self.width, self.height = rule1_bg_size[0], rule1_bg_size[1]
        self.rect.left, self.rect.top = \
                        (self.width - self.rect.width) // 2, \
                        self.height - self.rect.height - 60
        self.speed = 10
        self.active = True
        self.invincible = False
        self.mask = pygame.mask.from_surface(self.image1)

    def moveUp(self):
        if self.rect.top > 0:
            self.rect.top -= self.speed
        else:
            self.rect.top = 0

    def moveDown(self):
        if self.rect.bottom < self.height - 60:
            self.rect.top += self.speed
        else:
            self.rect.bottom = self.height - 60

    def moveLeft(self):
        if self.rect.left > 0:
            self.rect.left -= self.speed
        else:
            self.rect.left = 0

    def moveRight(self):
        if self.rect.right < self.width:
            self.rect.left += self.speed
        else:
            self.rect.right = self.width

    def reset(self):
        self.rect.left, self.rect.top = \
                        (self.width - self.rect.width) // 2, \
                        self.height - self.rect.height - 60
        self.active = True
        self.invincible = True


class SmallEnemy(pygame.sprite.Sprite):
    def __init__(self, rule1_bg_size):
        pygame.sprite.Sprite.__init__(self)
        
        self.image = pygame.image.load(pbinPath+"\\Z9\\rule1_images\\enemy1.png").convert_alpha()
        self.destroy_images = []
        self.destroy_images.extend([ \
            pygame.image.load(pbinPath+"\\Z9\\rule1_images\\enemy1_down1.png").convert_alpha(), \
            pygame.image.load(pbinPath+"\\Z9\\rule1_images\\enemy1_down2.png").convert_alpha(), \
            pygame.image.load(pbinPath+"\\Z9\\rule1_images\\enemy1_down3.png").convert_alpha(), \
            pygame.image.load(pbinPath+"\\Z9\\rule1_images\\enemy1_down4.png").convert_alpha() \
            ])
        self.rect = self.image.get_rect()
        self.width, self.height = rule1_bg_size[0], rule1_bg_size[1]
        self.speed = 2
        self.active = True
        self.rect.left, self.rect.top = \
            randint(0, self.width - self.rect.width), \
            randint(-5 * self.height, 0)
        self.mask = pygame.mask.from_surface(self.image)
    
    def move(self):
        if self.rect.top < self.height:
            self.rect.top += self.speed
        else:
            self.reset()
    
    def reset(self):
        self.active = True
        self.rect.left, self.rect.top = \
            randint(0, self.width - self.rect.width), \
            randint(-5 * self.height, 0)

class MidEnemy(pygame.sprite.Sprite):
    energy = 8
    
    def __init__(self, rule1_bg_size):
        pygame.sprite.Sprite.__init__(self)
        
        self.image = pygame.image.load(pbinPath+"\\Z9\\rule1_images\\enemy2.png").convert_alpha()
        self.image_hit = pygame.image.load(pbinPath+"\\Z9\\rule1_images\\enemy2_hit.png").convert_alpha()
        self.destroy_images = []
        self.destroy_images.extend([ \
            pygame.image.load(pbinPath+"\\Z9\\rule1_images\\enemy2_down1.png").convert_alpha(), \
            pygame.image.load(pbinPath+"\\Z9\\rule1_images\\enemy2_down2.png").convert_alpha(), \
            pygame.image.load(pbinPath+"\\Z9\\rule1_images\\enemy2_down3.png").convert_alpha(), \
            pygame.image.load(pbinPath+"\\Z9\\rule1_images\\enemy2_down4.png").convert_alpha() \
            ])
        self.rect = self.image.get_rect()
        self.width, self.height = rule1_bg_size[0], rule1_bg_size[1]
        self.speed = 1
        self.active = True
        self.rect.left, self.rect.top = \
            randint(0, self.width - self.rect.width), \
            randint(-10 * self.height, -self.height)
        self.mask = pygame.mask.from_surface(self.image)
        self.energy = MidEnemy.energy
        self.hit = False
    
    def move(self):
        if self.rect.top < self.height:
            self.rect.top += self.speed
        else:
            self.reset()
    
    def reset(self):
        self.active = True
        self.energy = MidEnemy.energy
        self.rect.left, self.rect.top = \
            randint(0, self.width - self.rect.width), \
            randint(-10 * self.height, -self.height)


class BigEnemy(pygame.sprite.Sprite):
    energy = 20
    
    def __init__(self, rule1_bg_size):
        pygame.sprite.Sprite.__init__(self)
        
        self.image1 = pygame.image.load(pbinPath+"\\Z9\\rule1_images\\enemy3_n1.png").convert_alpha()
        self.image2 = pygame.image.load(pbinPath+"\\Z9\\rule1_images\\enemy3_n2.png").convert_alpha()
        self.image_hit = pygame.image.load(pbinPath+"\\Z9\\rule1_images\\enemy3_hit.png").convert_alpha()
        self.destroy_images = []
        self.destroy_images.extend([ \
            pygame.image.load(pbinPath+"\\Z9\\rule1_images\\enemy3_down1.png").convert_alpha(), \
            pygame.image.load(pbinPath+"\\Z9\\rule1_images\\enemy3_down2.png").convert_alpha(), \
            pygame.image.load(pbinPath+"\\Z9\\rule1_images\\enemy3_down3.png").convert_alpha(), \
            pygame.image.load(pbinPath+"\\Z9\\rule1_images\\enemy3_down4.png").convert_alpha(), \
            pygame.image.load(pbinPath+"\\Z9\\rule1_images\\enemy3_down5.png").convert_alpha(), \
            pygame.image.load(pbinPath+"\\Z9\\rule1_images\\enemy3_down6.png").convert_alpha() \
            ])
        self.rect = self.image1.get_rect()
        self.width, self.height = rule1_bg_size[0], rule1_bg_size[1]
        self.speed = 1
        self.active = True
        self.rect.left, self.rect.top = \
            randint(0, self.width - self.rect.width), \
            randint(-15 * self.height, -5 * self.height)
        self.mask = pygame.mask.from_surface(self.image1)
        self.energy = BigEnemy.energy
        self.hit = False
    
    def move(self):
        if self.rect.top < self.height:
            self.rect.top += self.speed
        else:
            self.reset()
    
    def reset(self):
        self.active = True
        self.energy = BigEnemy.energy
        self.rect.left, self.rect.top = \
            randint(0, self.width - self.rect.width), \
            randint(-15 * self.height, -5 * self.height)


class Bullet1(pygame.sprite.Sprite):
    def __init__(self, position):
        pygame.sprite.Sprite.__init__(self)
        global  rule1_bg_size
        self.image = pygame.image.load(pbinPath+"\\Z9\\rule1_images\\bullet1.png").convert_alpha()
        self.rect = self.image.get_rect()
        self.rect.left, self.rect.top = position
        #self.rect.left = position[0]
        #self.rect.top = rule1_bg_size[1]+500
        self.speed = 12
        self.active = False
        self.mask = pygame.mask.from_surface(self.image)
    
    def move(self):
        self.rect.top -= self.speed
        #print("AA_Bullet1  self.rect.top="+str(self.rect.top))
        if self.rect.top < 0:
            self.active = False
    
    def reset(self, position):
        self.rect.left, self.rect.top = position
        self.active = True


class Bullet2(pygame.sprite.Sprite):
    def __init__(self, position):
        pygame.sprite.Sprite.__init__(self)
        
        self.image = pygame.image.load(pbinPath+"\\Z9\\rule1_images\\bullet2.png").convert_alpha()
        self.rect = self.image.get_rect()
        self.rect.left, self.rect.top = position
        self.speed = 14
        self.active = False
        self.mask = pygame.mask.from_surface(self.image)
    
    def move(self):
        self.rect.top -= self.speed
        
        if self.rect.top < 0:
            self.active = False
    
    def reset(self, position):
        self.rect.left, self.rect.top = position
        self.active = True


class Bullet_Supply(pygame.sprite.Sprite):
    def __init__(self, rule1_bg_size):
        pygame.sprite.Sprite.__init__(self)
        global pbinPath
        self.image = pygame.image.load(pbinPath+"\\Z9\\rule1_images\\bullet_supply.png").convert_alpha()
        self.rect = self.image.get_rect()
        self.width, self.height = rule1_bg_size[0], rule1_bg_size[1]
        self.rect.left, self.rect.bottom = \
            randint(0, self.width - self.rect.width), -100
        self.speed = 5
        self.active = False
        self.mask = pygame.mask.from_surface(self.image)
    
    def move(self):
        if self.rect.top < self.height:
            #print("AA_move  self.height="+str(self.height))
            self.rect.top += self.speed
        else:
            self.active = False
    
    def reset(self):
        self.active = True
        #print("AA_reset  self.height="+str(self.height))
        self.rect.left, self.rect.bottom = \
            randint(0, self.width - self.rect.width), -100


class Bomb_Supply(pygame.sprite.Sprite):
    def __init__(self, rule1_bg_size):
        pygame.sprite.Sprite.__init__(self)
        
        self.image = pygame.image.load(pbinPath+"\\Z9\\rule1_images\\bomb_supply.png").convert_alpha()
        self.rect = self.image.get_rect()
        self.width, self.height = rule1_bg_size[0], rule1_bg_size[1]
        self.rect.left, self.rect.bottom = \
            randint(0, self.width - self.rect.width), -100
        self.speed = 5
        self.active = False
        self.mask = pygame.mask.from_surface(self.image)
    
    def move(self):
        if self.rect.top < self.height:
            self.rect.top += self.speed
        else:
            self.active = False
    
    def reset(self):
        self.active = True
        self.rect.left, self.rect.bottom = \
            randint(0, self.width - self.rect.width), -100

# ═════════════════════════ Rule_2 Pre Define
class PlaneWars_Rule_2(Basic_Rule):

    def __init__(self, rule_index, operation_type):
        self.rule_index = rule_index
        self.operation_type = operation_type
        
        
        
        
    def initParamsWithInputList(self,inputParamList):


        return True
        
#    def __init__(self, rule_index, operation_type,file_type):
#        self.rule_index = rule_index
#        self.operation_type = operation_type
#        self.file_type = file_type


    def applyNoParamOperationRule0(self):
        try:
        # 载入游戏音乐
            global rule1_screen
            global pbinPath
            global rule1_background        
            global rule1_bullet_sound      
            global rule1_bomb_sound        
            global rule1_supply_sound      
            global get_rule1_bomb_sound    
            global get_rule1_bullet_sound  
            global rule1_upgrade_sound     
            global rule1_enemy3_fly_sound  
            global rule1_enemy1_down_sound 
            global rule1_enemy2_down_sound 
            global rule1_enemy3_down_sound 
            global rule1_me_down_sound   
            
            pygame.init()
            screenInfo = pygame.display.Info()
            PANEL_width = screenInfo.current_w
            PANEL_highly = screenInfo.current_h
    
            #  480, 700 模式 Begin 
            #rule1_screen =pygame.display.set_mode(rule1_bg_size)  ##  480, 700
            # 480, 700 模式 End 
            
            
            # 全屏模式 Begin 
            rule1_screen =pygame.display.set_mode((PANEL_width, PANEL_highly))  
            global rule1_bg_size
            global rule1_width
            global rule1_height
            rule1_width = PANEL_width
            rule1_height = PANEL_highly
            rule1_bg_size = PANEL_width, PANEL_highly
            rule1_bg_size = rule1_width, rule1_height = PANEL_width, PANEL_highly
            
            # 全屏模式 End
            
            pygame.display.set_caption("飞机大战 -- FishC Demo")
            pygame.mixer.init()
            
            rule1_background        = pygame.image.load(str(pbinPath)+"\\Z9\\rule1_images\\background.png").convert()
            rule1_bullet_sound      = pygame.mixer.Sound(pbinPath+"\\Z9\\rule1_sound\\bullet.wav")
            rule1_bomb_sound        = pygame.mixer.Sound(pbinPath+"\\Z9\\rule1_sound\\use_bomb.wav")
            rule1_supply_sound      = pygame.mixer.Sound(pbinPath+"\\Z9\\rule1_sound\\supply.wav")
            get_rule1_bomb_sound    = pygame.mixer.Sound(pbinPath+"\\Z9\\rule1_sound\\get_bomb.wav")
            get_rule1_bullet_sound  = pygame.mixer.Sound(pbinPath+"\\Z9\\rule1_sound\\get_bullet.wav")
            rule1_upgrade_sound     = pygame.mixer.Sound(pbinPath+"\\Z9\\rule1_sound\\upgrade.wav")
            rule1_enemy3_fly_sound  = pygame.mixer.Sound(pbinPath+"\\Z9\\rule1_sound\\enemy3_flying.wav")
            rule1_enemy1_down_sound = pygame.mixer.Sound(pbinPath+"\\Z9\\rule1_sound\\enemy1_down.wav")
            rule1_enemy2_down_sound = pygame.mixer.Sound(pbinPath+"\\Z9\\rule1_sound\\enemy2_down.wav")
            rule1_enemy3_down_sound = pygame.mixer.Sound(pbinPath+"\\Z9\\rule1_sound\\enemy3_down.wav")
            rule1_me_down_sound     = pygame.mixer.Sound(pbinPath+"\\Z9\\rule1_sound\\me_down.wav")
            pygame.mixer.music.load(pbinPath+"\\Z9\\rule1_sound\\game_music.ogg")
            pygame.mixer.music.set_volume(0.2)
            rule1_bullet_sound.set_volume(0.2)
            rule1_bomb_sound.set_volume(0.2)
            rule1_supply_sound.set_volume(0.2)
            get_rule1_bomb_sound.set_volume(0.2)
            get_rule1_bullet_sound.set_volume(0.2)
            rule1_upgrade_sound.set_volume(0.2)
            rule1_enemy3_fly_sound.set_volume(0.2)
            rule1_enemy1_down_sound.set_volume(0.2)
            rule1_enemy2_down_sound.set_volume(0.2)
            rule1_enemy3_down_sound.set_volume(0.5)
            rule1_me_down_sound.set_volume(0.2)
            rule_1_main()
        except SystemExit:
            pass
        except:
            traceback.print_exc()
            pygame.quit()
            input()

    def simpleDesc(self):
        return "飞机大战(微信版本)"

###################### Rule_2 End  ######################


###################### Rule_3 Begin  ######################
seed(1)

class SnakeConf(object):
    # 蛇运动的场地长宽
    global Device_Height
    global Device_Width
    WIDTH , HEIGHT= 27, 27     ##  一个方块的宽高像素
    LINE_WIDTH = 27
    LINE_MARGIN = 2
    LINE_TRUEWIDTH = LINE_WIDTH - 2 * LINE_MARGIN
    # SCREEN_X, SCREEN_Y = LINE_WIDTH * HEIGHT, LINE_WIDTH * WIDTH
    # SCREEN_X, SCREEN_Y =  int((Device_Width/25)-LINE_MARGIN) * HEIGHT   ,int((Device_Height/25)-LINE_MARGIN) * HEIGHT
    SCREEN_X, SCREEN_Y =  LINE_WIDTH * WIDTH    , LINE_WIDTH * HEIGHT 
    #FIELD_SIZE = HEIGHT * WIDTH
    FIELD_SIZE = HEIGHT * WIDTH
    # 用来代表不同东西的数字，由于矩阵上每个格子会处理成到达食物的路径长度，
    # 因此这三个变量间需要有足够大的间隔(>HEIGHT*WIDTH)
    random.seed(time.clock())
    # FOOD, UNDEFINED = 0, (HEIGHT + 1) * (WIDTH + 1)
    FOOD, UNDEFINED = 0, random.randint(0,HEIGHT + 1) * (WIDTH + 1)

    SNAKE = 2 * UNDEFINED

    LEFT, RIGHT, UP, DOWN = -1, 1, -WIDTH, WIDTH

    # 错误码
    ERR = SNAKE
    BLACK_COLOR = (0, 0, 0)
    WHITE_COLOR = (255, 255, 255)
    SNAKE_COLOR = (136, 0, 21)
    FOOD_COLOR = (20, 220, 39)
    RED_COLOR = (255, 0, 0)

class Snake(object):

    def __init__(self):

        # 用一维数组来表示二维的东西
        # board表示蛇运动的矩形场地
        # 初始化蛇头在(1,1)的地方，第0行，HEIGHT行，第0列，WIDTH列为围墙，不可用
        # 初始蛇长度为1
        self.board = [0] * SnakeConf.FIELD_SIZE
        self.snake = [0] * (SnakeConf.FIELD_SIZE + 1)
        self.snake[0] = 1 * SnakeConf.WIDTH + 1
        self.snake_size = 1

        self.desperate = False

        # 与上面变量对应的临时变量，蛇试探性地移动时使用
        self.tmpboard = [0] * SnakeConf.FIELD_SIZE
        self.tmpsnake = [0] * (SnakeConf.FIELD_SIZE + 1)
        self.tmpsnake[0] = 1 * SnakeConf.WIDTH + 1
        self.tmpsnake_size = 1

        # food:食物位置(0~FIELD_SIZE-1),初始在(3, 3)
        self.food = 3 * SnakeConf.WIDTH + 3

        # 运动方向数组
        self.mov = [SnakeConf.LEFT, SnakeConf.RIGHT, SnakeConf.UP, SnakeConf.DOWN]

        self.desperate = False

    def show_text(self, screen, pos, text, color, font_bold = False, font_size = 60, font_italic = False):
        #获取系统字体，并设置文字大小
        cur_font = pygame.font.SysFont("宋体", font_size)
        #设置是否加粗属性
        cur_font.set_bold(font_bold)
        #设置是否斜体属性
        cur_font.set_italic(font_italic)
        #设置文字内容
        text_fmt = cur_font.render(text, 1, color)
        #绘制文字
        screen.blit(text_fmt, pos)

    def is_cell_free(self, idx, psize, psnake):
        # 检查一个cell有没有被蛇身覆盖，没有覆盖则为free，返回true
        return not (idx in psnake[:psize])

    # 检查某个位置idx是否可向move方向运动
    def is_move_possible(self, idx, move):
        flag = False
        if move == SnakeConf.LEFT:
            flag = idx % SnakeConf.WIDTH > 1
        elif move == SnakeConf.RIGHT:
            flag = idx % SnakeConf.WIDTH < (SnakeConf.WIDTH - 2)
        elif move == SnakeConf.UP:
            flag = idx > (2 * SnakeConf.WIDTH - 1) # 即idx/WIDTH > 1
        elif move == SnakeConf.DOWN:
            flag = idx < (SnakeConf.FIELD_SIZE - 2 * SnakeConf.WIDTH) # 即idx/WIDTH < HEIGHT-2
        return flag

    # 重置board
    # board_refresh后，UNDEFINED值都变为了到达食物的路径长度
    # 如需要还原，则要重置它
    def board_reset(self, psnake, psize, pboard):
        for i in range(SnakeConf.FIELD_SIZE):
            if i == self.food:
                pboard[i] = SnakeConf.FOOD
            elif self.is_cell_free(i, psize, psnake): # 该位置为空
                pboard[i] = SnakeConf.UNDEFINED
            else: # 该位置为蛇身
                pboard[i] = SnakeConf.SNAKE
    
    # 广度优先搜索遍历整个board，
    # 计算出board中每个非SNAKE元素到达食物的路径长度
    # 返回当前能否到达食物
    def board_refresh(self, pfood, psnake, pboard):
        queue = []
        queue.append(pfood)
        inqueue = [0] * SnakeConf.FIELD_SIZE #inque表示是否检查过这个点
        found = False
        # while循环结束后，除了蛇的身体，
        # 其它每个方格中的数字代码从它到食物的路径长度
        while len(queue) != 0:
            idx = queue.pop(0)
            if inqueue[idx] == 1:
                continue
            inqueue[idx] = 1
            for move in self.mov:
                if self.is_move_possible(idx, move):
                    if idx + move == psnake[0]:
                        found = True
                    if pboard[idx+move] < SnakeConf.SNAKE: # 如果该点不是蛇的身体
                        if pboard[idx + move] > pboard[idx] + 1:
                            pboard[idx + move] = pboard[idx] + 1
                        if inqueue[idx + move] == 0:
                            queue.append(idx + move)

        return found

    # 从蛇头开始，根据board中元素值，
    # 从蛇头周围4个领域点中选择最短路径
    def choose_shortest_safe_move(self, psnake, pboard):
        best_move = SnakeConf.ERR
        min = SnakeConf.UNDEFINED
        for move in self.mov:
            if self.is_move_possible(psnake[0], move) \
                    and pboard[psnake[0] + move] < min:
                min = pboard[psnake[0] + move]
                best_move = move
        return best_move

    # 从蛇头开始，根据board中元素值，
    # 从蛇头周围4个领域点中选择最远路径
    def choose_longest_safe_move(self, psnake, pboard):
        best_move = SnakeConf.ERR
        max = -1
        for move in self.mov:
            if self.is_move_possible(psnake[0], move) \
                    and max < pboard[psnake[0] + move] < SnakeConf.UNDEFINED:
                max = pboard[psnake[0] + move]
                best_move = move
        return best_move

    # 检查是否可以追着蛇尾运动，即蛇头和蛇尾间是有路径的
    # 为的是避免蛇头陷入死路
    # 虚拟操作，在tmpboard,tmpsnake中进行
    def is_tail_inside(self):
        self.tmpboard[self.tmpsnake[self.tmpsnake_size - 1]] = SnakeConf.FOOD # 虚拟地将蛇尾变为食物(因为是虚拟的，所以在tmpsnake,tmpboard中进行)
        self.tmpboard[self.food] = SnakeConf.SNAKE # 放置食物的地方，看成蛇身
        result = self.board_refresh(self.tmpsnake[self.tmpsnake_size-1], self.tmpsnake, self.tmpboard) # 求得每个位置到蛇尾的路径长度
        if not result: # 如果没有路直接返回
            return False

        if self.desperate:
            result = False
            for move in self.mov: # 如果蛇头和蛇尾紧挨着，则返回False。即不能follow_tail，追着蛇尾运动了
                if self.is_move_possible(self.tmpsnake[0], move) \
                        and self.tmpsnake[0] + move != self.tmpsnake[self.tmpsnake_size - 1]: # \
                    result = True
                else:
                    self.tmpboard[self.tmpsnake[0] + move] = SnakeConf.UNDEFINED # 不符合
        else: #蛇绝望前，走得比较保守，只要蛇头和蛇尾挨着，就不走
            for move in self.mov: # 如果蛇头和蛇尾紧挨着，则返回False。即不能follow_tail，追着蛇尾运动了
                if self.is_move_possible(self.tmpsnake[0], move) \
                        and self.tmpsnake[0] + move == self.tmpsnake[self.tmpsnake_size - 1] \
                        and self.tmpsnake_size > 3: # 如果不限制3的话，长度为1的蛇就不会再吃，seed(101)
                    result = False

        return result

    # 让蛇头朝着蛇尾运行一步
    # 不管蛇身阻挡，朝蛇尾方向运行
    def follow_tail(self):
        self.tmpsnake_size = self.snake_size
        self.tmpsnake = self.snake[:]
        self.board_reset(self.tmpsnake, self.tmpsnake_size, self.tmpboard) # 重置虚拟board
        self.tmpboard[self.tmpsnake[self.tmpsnake_size - 1]] = SnakeConf.FOOD # 让蛇尾成为食物
        self.tmpboard[self.food] = SnakeConf.SNAKE # 让食物的地方变成蛇身
        self.board_refresh(self.tmpsnake[self.tmpsnake_size - 1], self.tmpsnake, self.tmpboard) # 求得各个位置到达蛇尾的路径长度
        self.tmpboard[self.tmpsnake[self.tmpsnake_size - 1]] = SnakeConf.SNAKE # 还原蛇尾

        return self.choose_longest_safe_move(self.tmpsnake, self.tmpboard) # 返回运行方向(让蛇头运动1步)

    # 在各种方案都不行时，随便找一个可行的方向来走(1步),
    def any_possible_move(self):
        best_move = SnakeConf.ERR
        self.board_reset(self.snake, self.snake_size, self.board)
        self.board_refresh(self.food, self.snake, self.board)
        min = SnakeConf.SNAKE

        for move in self.mov:
            if self.is_move_possible(self.snake[0], move) \
                    and self.board[self.snake[0] + move] < min:
                min = self.board[self.snake[0] + move]
                best_move = move
        return best_move

    def shift_array(self, arr, size):
        arr[1:size + 1] = arr[:size]

    def new_food(self):
        while True:
            w = randint(1, SnakeConf.WIDTH-2)
            h = randint(1, SnakeConf.HEIGHT-2)
            food = h * SnakeConf.WIDTH + w
            cell_free = self.is_cell_free(food, self.snake_size, self.snake)
            if cell_free:
                self.food = food
                return

    # 真正的蛇在这个函数中，朝pbest_move走1步
    def make_move(self, pbest_move):
        self.shift_array(self.snake, self.snake_size)
        self.snake[0] += pbest_move

        # 如果新加入的蛇头就是食物的位置
        # 蛇长加1，产生新的食物，重置board(因为原来那些路径长度已经用不上了)
        if self.snake[0] == self.food:
            self.board[self.snake[0]] = SnakeConf.SNAKE # 新的蛇头
            self.snake_size += 1
            if self.snake_size < SnakeConf.FIELD_SIZE:
                self.new_food()
        else: # 如果新加入的蛇头不是食物的位置
            self.board[self.snake[0]] = SnakeConf.SNAKE # 新的蛇头
            self.board[self.snake[self.snake_size]] = SnakeConf.UNDEFINED # 蛇尾变为空格

    # 虚拟地运行一次，然后在调用处检查这次运行可否可行
    # 可行才真实运行。
    # 虚拟运行吃到食物后，得到虚拟下蛇在board的位置
    def virtual_shortest_move(self):
        self.tmpsnake_size = self.snake_size
        self.tmpsnake = self.snake[:] # 如果直接tmpsnake=snake，则两者指向同一处内存
        self.tmpboard = self.board[:] # board中已经是各位置到达食物的路径长度了，不用再计算
        self.board_reset(self.tmpsnake, self.tmpsnake_size, self.tmpboard)

        food_eated = False
        while not food_eated:
            self.board_refresh(self.food, self.tmpsnake, self.tmpboard)
            move = self.choose_shortest_safe_move(self.tmpsnake, self.tmpboard)
            self.shift_array(self.tmpsnake, self.tmpsnake_size)
            self.tmpsnake[0] += move # 在蛇头前加入一个新的位置
            # 如果新加入的蛇头的位置正好是食物的位置
            # 则长度加1，重置board，食物那个位置变为蛇的一部分(SNAKE)
            if self.tmpsnake[0] == self.food:
                self.tmpsnake_size += 1
                self.board_reset(self.tmpsnake, self.tmpsnake_size, self.tmpboard) # 虚拟运行后，蛇在board的位置(label101010)
                self.tmpboard[self.food] = SnakeConf.SNAKE
                food_eated = True
            else: # 如果蛇头不是食物的位置，则新加入的位置为蛇头，最后一个变为空格
                self.tmpboard[self.tmpsnake[0]] = SnakeConf.SNAKE
                self.tmpboard[self.tmpsnake[self.tmpsnake_size]] = SnakeConf.UNDEFINED

    def draw_snake(self, screen):
        global Device_Width; 
        left = (self.snake[0] // SnakeConf.WIDTH) * SnakeConf.LINE_WIDTH + 2 + int(Device_Width // 4) # margin 2
        top = (self.snake[0] % SnakeConf.WIDTH) * SnakeConf.LINE_WIDTH + 2
        rect = pygame.Rect(left, top, SnakeConf.LINE_TRUEWIDTH, SnakeConf.LINE_TRUEWIDTH)
        pygame.draw.rect(screen, SnakeConf.RED_COLOR, rect, 0)
        for i in range(1, self.snake_size):
            left_pre = (self.snake[i - 1] // SnakeConf.WIDTH) * SnakeConf.LINE_WIDTH + 2  + int(Device_Width // 4) # margin 2
            top_pre = (self.snake[i - 1] % SnakeConf.WIDTH) * SnakeConf.LINE_WIDTH + 2
            left = (self.snake[i] // SnakeConf.WIDTH) * SnakeConf.LINE_WIDTH + 2   + int(Device_Width // 4) # margin 2
            top = (self.snake[i] % SnakeConf.WIDTH) * SnakeConf.LINE_WIDTH + 2
            if (top_pre == top):
                rect = pygame.Rect(min(left, left_pre), top, SnakeConf.LINE_TRUEWIDTH + SnakeConf.LINE_WIDTH, SnakeConf.LINE_TRUEWIDTH)
            else:
                rect = pygame.Rect(left, min(top, top_pre), SnakeConf.LINE_TRUEWIDTH, SnakeConf.LINE_TRUEWIDTH + SnakeConf.LINE_WIDTH)
            if (i == 1):
                pygame.draw.rect(screen, SnakeConf.BLACK_COLOR , rect, 0)
            else:
                pygame.draw.rect(screen, SnakeConf.SNAKE_COLOR, rect, 0)
    def handle_key_event(self, event):
        if event.type == KEYDOWN:
            if event.key == K_ESCAPE:
                sys.exit()
                
    def snake_main(self):
        pygame.init()
        
        global Device_Width
        global Device_Height

        # screen = pygame.display.set_mode((SnakeConf.SCREEN_X, SnakeConf.SCREEN_Y))
        screen = pygame.display.set_mode((Device_Width, Device_Height))
        pygame.display.set_caption('AI_Snake')
        clock = pygame.time.Clock()

        isdead = False

        dead_loop = 0
        pre_score = 0

        pause = False
        onestep = False
        while True:
            for event in pygame.event.get():

                if event.type == KEYDOWN and event.key == pygame.K_ESCAPE:
                    sys.exit()
                if event.type == pygame.QUIT:
                    sys.exit()
                if event.type == pygame.KEYDOWN and event.key == pygame.K_SPACE:
                    pause = not pause
                if event.type == pygame.KEYDOWN and event.key == pygame.K_RIGHT:
                    onestep = True

            if pause and not onestep:
                continue

            onestep = False

            screen.fill(SnakeConf.BLACK_COLOR)

            boardrect = pygame.Rect(SnakeConf.LINE_WIDTH+ int(Device_Width // 4),SnakeConf.LINE_WIDTH,SnakeConf.SCREEN_X - SnakeConf.LINE_WIDTH*2 , SnakeConf.SCREEN_Y-SnakeConf.LINE_WIDTH*2  )
            pygame.draw.rect(screen,SnakeConf.WHITE_COLOR,boardrect,0)
            
            self.draw_snake(screen)

            rect = pygame.Rect((self.food//SnakeConf.WIDTH)*SnakeConf.LINE_WIDTH + 2+ int(Device_Width // 4),(self.food%SnakeConf.WIDTH)*SnakeConf.LINE_WIDTH +2 ,SnakeConf.LINE_TRUEWIDTH, SnakeConf.LINE_TRUEWIDTH)
            pygame.draw.rect(screen,SnakeConf.FOOD_COLOR,rect,0)


  
            # 重置矩阵
            self.board_reset(self.snake, self.snake_size, self.board)

            # 如果蛇可以吃到食物，board_refresh返回true
            # 并且board中除了蛇身(=SNAKE)，其它的元素值表示从该点运动到食物的最短路径长
            if self.board_refresh(self.food, self.snake, self.board):
                # 虚拟地运行一次，因为已经确保蛇与食物间有路径，所以执行有效
                # 运行后得到虚拟下蛇在board中的位置，即tmpboard
                self.virtual_shortest_move()  # 该函数唯一调用处，在虚拟的board
                if self.is_tail_inside():  # 如果虚拟运行后，蛇头蛇尾间有通路，则选最短路运行(1步)
                    best_move = self.choose_shortest_safe_move(self.snake, self.board)
                else:
                    best_move = self.follow_tail()  # 否则虚拟地follow_tail 1步，如果可以做到
            else:
                best_move = self.follow_tail()

            if best_move == SnakeConf.ERR:
                best_move = self.any_possible_move()
            # 上面一次思考，只得出一个方向，运行一步
            if best_move != SnakeConf.ERR:
                self.make_move(best_move)
            else:
                isdead = True

            if isdead:
                self.show_text(screen,(100,200),'YOU DEAD!',(227,29,18), False, 100)
                self.show_text(screen,(150,260),'press space to try again...',(0,0,22), False, 30)
                time.sleep(10)
                snake1 = Snake()
                snake1.snake_main()
            # 显示分数文字
            self.show_text(screen,(50,500),'Scores: '+str(self.snake_size),(223,223,223))

            pygame.display.update()
            if (self.snake_size == pre_score):
                dead_loop += 1
            else:
                dead_loop = 0
            if dead_loop > SnakeConf.FIELD_SIZE:
                self.desperate = True # 蛇绝望了
                print('desperate')
            pre_score = self.snake_size

            clock.tick(100) # 这里调的是帧律

class AI_SnakeEatFood_Rule_3(Basic_Rule):

    def __init__(self, rule_index, operation_type):
        self.rule_index = rule_index
        self.operation_type = operation_type
        
        
    def initParamsWithInputList(self,inputParamList):
        return True
        
    def applyNoParamOperationRule0(self):
        snake1 = Snake()
        snake1.snake_main()
        
    def simpleDesc(self):
        return "AI智恩跑贪吃蛇(只可远观不可玩)"
###################### Rule_3 End  ######################




###################### Rule_4 End  ######################

Rule4_WHITE = (255, 255, 255)
Rule4_BLACK = (0, 0, 0)
Rule4_RED = (255, 0, 0)
Rule4_GREEN = (20, 160, 15)
Rule4_USIZE = 20 # 单位长度
Rule4_ROWS = 25
Rule4_COLUMNS = 25
Rule4_INITSPEED = 15  # 蛇的速度

class Rule4_SnakeGame:

    # 游戏状态
    class GameState:
        def __init__(self):
            global Rule4_COLUMNS
            global Rule4_USIZE
            global Rule4_ROWS
            global Rule4_INITSPEED
            self.GAMESTATE = 'playing'
            self.screen_size = Rule4_USIZE * Rule4_COLUMNS + 300, Rule4_USIZE * Rule4_ROWS  # 屏幕尺寸
            self.score = 0  # 得分
            self.full_screen = False  # 是否全屏
            self.speed = Rule4_INITSPEED # FPS，游戏速度

        def add_score(self):
            self.score += 1
            if self.speed < 15:
                self.speed *= 1.1
            if self.score in (30, 50, 65, 75) or\
                (self.score > 75 and (self.score - 80) % 5 == 0):
                Rule4_sound_manager.play_cheer_sound()

    # 处理与食物有关的事情
    class Rule4_FoodManager:
        def __init__(self):
            self.fruit_showing = False  # 当前是否有食物
            self.fruit_pos = None  # 食物位置

        def draw_fruit(self, screen, snake):
            """生成并绘制食物"""
            if not self.fruit_showing:
                tempPos = None
                while not tempPos or tempPos in snake.bodyList:
                    fX = random.randint(0, Rule4_ROWS-1)
                    fY = random.randint(0, Rule4_ROWS-1)
                    tempPos = (fX, fY)
                self.fruit_pos = tempPos
            pygame.draw.rect(screen, Rule4_RED, \
                    (self.fruit_pos[0]*Rule4_USIZE, self.fruit_pos[1]*Rule4_USIZE, Rule4_USIZE, Rule4_USIZE))
            self.fruit_showing = True

        def hide_fruit(self):
            self.fruit_showing = False


    def __init__(self):
        pygame.init()

        self.gs = self.GameState()
        self.fm = self.Rule4_FoodManager()

        self.new_direction_setted = False # 加锁，防止一个时间周期内改变两次方向，碰到蛇身第二节。

        self.snake = Rule4_Snake()  # 蛇对象

        self.fpsClock = pygame.time.Clock()
#
        self.fontObj = pygame.font.Font(pbinPath+"\\Z9\\rule4_file\\Kaiti_GB2312.ttf", 20)
          
        self.scoreFont = pygame.font.Font(pbinPath+"\\Z9\\rule4_file\\Kaiti_GB2312.ttf", 32)

        self.screen = self.new_screen(self.gs.screen_size)
        pygame.display.set_caption('贪吃蛇')

    def start(self):
        self.draw_board()
        snake = self.snake

        while True:
            if self.new_direction_setted:
                self.new_direction_setted = False
            for event in pygame.event.get():
                self.handle_key_event(event)

            if self.gs.GAMESTATE == 'playing':
                if snake.is_dead():
                    self.gameover()

                if self.snake_meet_food():
                    self.snake_eat_food()

                self.snake.moveForward()
                self.draw_board()
            elif self.gs.GAMESTATE == 'over':
                pygame.mixer.music.pause()
                self.drawFinal()
            pygame.display.update()
            self.fpsClock.tick(self.gs.speed)

    def handle_key_event(self, event):
        if event.type == KEYDOWN:
            if event.key == K_ESCAPE:
                sys.exit()
            if event.key == K_f:
                self.screen = self.new_screen(self.gs.screen_size, full=not self.gs.full_screen)

            if self.gs.GAMESTATE == 'over' and event.key == K_RETURN:
                print('Return press')
                self.initGame()
            if event.key == K_SPACE:
                if self.gs.GAMESTATE == 'playing':
                    self.gs.GAMESTATE = 'pausing'
                    pygame.mixer.music.pause()
                elif self.gs.GAMESTATE == 'pausing':
                    self.gs.GAMESTATE = 'playing'
                    pygame.mixer.music.unpause()
            if self.gs.GAMESTATE == 'playing' and not self.new_direction_setted:
                direction = ''
                if event.key in (K_DOWN, K_s):
                    direction = 'down'
                elif event.key in (K_UP, K_w):
                    direction = 'up'
                elif event.key in (K_LEFT, K_a):
                    direction = 'left'
                elif event.key in (K_RIGHT, K_d):
                    direction = 'right'
                if self.snake.isValidDirection(direction):
                    self.snake.changeDirection(direction)
                    self.new_direction_setted = True
        if event.type == QUIT:
            #  pygame.quit()
            sys.exit()

    def gameover(self):
        self.gs.GAMESTATE = 'over'
        Rule4_sound_manager.play_fail_sound()
        self.drawFinal()

    def new_screen(self, size, full=False):
        screen = None
        if full:
            self.gs.full_screen = True
            screen = pygame.display.set_mode(size, FULLSCREEN)
        else:
            self.gs.full_screen = False
            screen = pygame.display.set_mode(size)
        return screen

    # 辅助函数
    def initGame(self):
        """重新开始游戏时，对游戏初始化"""
        self.score = 0
        self.gs.GAMESTATE = 'playing'
        self.isFruitShowing = False
        self.fruitPos = None
        self.speed = Rule4_INITSPEED
        self.snake = Rule4_Snake()
        pygame.mixer.music.rewind()
        pygame.mixer.music.unpause()

    def drawFinal(self):
        screen = self.screen
        pygame.draw.rect(screen, Rule4_RED, \
                (200, 120, 400, 300))
        pygame.draw.rect(screen, Rule4_BLACK, \
                (210, 130, 380, 280))
        overText = self.scoreFont.render('GAME OVER!',\
                True, Rule4_WHITE)
        scoreText = self.scoreFont.render(u'最终得分: ' + str(self.gs.score),\
                True, Rule4_WHITE)
        promptText = self.fontObj.render(u'按 "回车键" 再玩一次',
                True, Rule4_WHITE)
        self.screen.blit(overText, (300, 200))
        self.screen.blit(scoreText, (300, 240))
        self.screen.blit(promptText, (300, 290))


    def snake_eat_food(self):
        self.snake.grow()
        self.fm.hide_fruit()
        Rule4_sound_manager.play_eat_sound()
        self.gs.add_score()


    def draw_board(self):
        self.screen.fill(Rule4_GREEN)
        # 分割线
        pygame.draw.line(self.screen, Rule4_RED, (502, 0), (502, 500), 3)
        promptText = self.fontObj.render('按 "空格键" 开始/暂停', True, Rule4_WHITE)
        #  promptText2 = fontObj.render(u'开始/暂停', True, Rule4_WHITE)
        scoreText = self.scoreFont.render('得分: ' + str(self.gs.score), True, Rule4_WHITE)
        self.screen.blit(promptText, (550, 100))
        #  screen.blit(promptText2, (560, 120))
        self.screen.blit(scoreText, (570, 220))
        # drawRule4_Snake()
        self.snake.draw_self(self.screen)
        self.fm.draw_fruit(self.screen, self.snake)

    # 蛇遇到食物了
    def snake_meet_food(self):
        return tuple(self.snake.headPos) == self.fm.fruit_pos

    def checkCollision(self):
        snake = self.snake
        # 吃到食物
        if tuple(snake.headPos) == self.fm.fruit_pos:
            Rule4_sound_manager.play_eat_sound()
            return 1
        # 碰到自己身体
        if tuple(snake.headPos) in snake.bodyList[1:]:
            return -1
        return 0



class Rule4_Snake:
    def __init__(self, length = 3, headPos = [5,3], direction = 'right'):
        """
        默认坐标系为25×25
        """
        self.cut_tale = True
        self.length = length
        self.headPos = headPos
        self.direction = direction

        self.bodyList = [tuple(headPos)] # 存储蛇身所在坐标
        tempX = headPos[0]
        tempY = headPos[1]
        for i in range(length - 1):
            if direction == 'right':
                tempX -= 1
            elif direction == 'left':
                tempX += 1
            elif direction == 'up':
                tempY -= 1
            else:
                tempY += 1
            self.bodyList.append((tempX, tempY))

    # 传入表示方向的字符串
    def changeDirection(self, newDirection):
        if self.isValidDirection(newDirection):
            self.direction = newDirection
        else:
            raise Exception('传入了错误的方向值')

    def isValidDirection(self, newDirection):
        if self.direction in ['up', 'down'] and newDirection in ['left', 'right']:
            return True
        elif self.direction in ['right', 'left'] and newDirection in ['up', 'down']:
            return True
        else:
            return False

    # 前进一步
    def moveForward(self):
        if self.direction == 'up':
            if self.headPos[1] == 0:
                self.headPos[1] = 24
            else:
                self.headPos[1] -= 1
        elif self.direction == 'down':
            if self.headPos[1] == 24:
                self.headPos[1] = 0
            else:
                self.headPos[1] += 1
        elif self.direction == 'right':
            if self.headPos[0] == 24:
                self.headPos[0] = 0
            else:
                self.headPos[0] += 1
        else:
            if self.headPos[0] == 0:
                self.headPos[0] = 24
            else:
                self.headPos[0] -= 1
        self.bodyList.insert(0, tuple(self.headPos))
        # if not eatFood:
        if self.cut_tale:
            self.bodyList.pop()
        else:
            self.cut_tale = True
        # else:
        # self.length += 1

    def grow(self):
        self.cut_tale = False
        # self.bodyList.insert(0, tuple(self.headPos))
        # self.length += 1

    def is_dead(self):
        return tuple(self.headPos) in self.bodyList[1:]


    def draw_self(self, screen):
        for pos in self.bodyList:
            pygame.draw.rect(screen, Rule4_WHITE, \
                    (pos[0]*Rule4_USIZE, pos[1]*Rule4_USIZE, Rule4_USIZE, Rule4_USIZE))


class Rule4_SoundManager:
    def __init__(self):
        self._init_music()
        self.sound_eat = self._loadSound(pbinPath+"\\Z9\\rule4_file\\eat.ogg")
        self.sound_fail = self._loadSound(pbinPath+"\\Z9\\rule4_file\\gameover.ogg") 
        self.sound_jiayou = self._loadSound(pbinPath+"\\Z9\\rule4_file\\jiayou.ogg") 

    # 背景音乐
    def _init_music(self):
        pygame.mixer.init()
        global pbinPath
        pygame.mixer.music.load(pbinPath+"\\Z9\\rule4_file\\background.mp3")  
        #  pygame.mixer.music.load('res/bg.mp3')
        pygame.mixer.music.set_volume(0.2)
        pygame.mixer.music.play(-1)

    def _loadSound(self, fname):
        return pygame.mixer.Sound(os.path.join('res', fname))

    def play_eat_sound(self):
        self.sound_eat.play()

    def play_fail_sound(self):
        self.sound_fail.play()

    def play_cheer_sound(self):
        self.sound_jiayou.play()



Rule4_sound_manager = ""
class SnakeEatFood_Rule_4(Basic_Rule):

    def __init__(self, rule_index, operation_type):
        self.rule_index = rule_index
        self.operation_type = operation_type
        
        
    def initParamsWithInputList(self,inputParamList):
        return True
        
    def applyNoParamOperationRule0(self):
        global Rule4_sound_manager
        Rule4_sound_manager = Rule4_SoundManager()
        game = Rule4_SnakeGame()
        game.start()
        
    def simpleDesc(self):
        return "贪吃蛇-可玩"

	
###################### Rule_4 End  ######################




###################### Rule_5 Begin  ###################### 
class Rule5_Bullet(pygame.sprite.Sprite):
    def __init__(self):
        pygame.sprite.Sprite.__init__(self)
        
        self.bullet_up = pygame.image.load(pbinPath+"\\Z9\\rule5_file\\bullet_up.png")
        self.bullet_down = pygame.image.load(pbinPath+"\\Z9\\rule5_file\\bullet_down.png")
        self.bullet_left = pygame.image.load(pbinPath+"\\Z9\\rule5_file\\bullet_left.png")
        self.bullet_right = pygame.image.load(pbinPath+"\\Z9\\rule5_file\\bullet_right.png")

        # 子弹方向   速度   生命   碎石
        self.dir_x, self.dir_y = 0, 0
        self.speed  = 6
        self.life   = False
        self.strong = False

        self.bullet = self.bullet_up
        self.rect = self.bullet.get_rect()
        self.rect.left, self.rect.right = 3 + 12 * 24, 3 + 24 * 24
    
    def changeImage(self, dir_x, dir_y):
        self.dir_x, self.dir_y = dir_x, dir_y
        if self.dir_x == 0 and self.dir_y == -1:
            self.bullet = self.bullet_up
        elif self.dir_x == 0 and self.dir_y == 1:
            self.bullet = self.bullet_down
        elif self.dir_x == -1 and self.dir_y == 0:
            self.bullet = self.bullet_left
        elif self.dir_x == 1 and self.dir_y == 0:
            self.bullet = self.bullet_right
        

    
    def move(self):
        self.rect = self.rect.move(self.speed * self.dir_x,
                                   self.speed * self.dir_y)
                
        # 碰撞地图边缘
        if self.rect.top < 3:
            self.life = False
        #    self.rect.left, self.rect.right = 3 + 12 * 24, 3 + 24 * 24
        if self.rect.bottom > 630 - 3:
            self.life = False
        #    self.rect.left, self.rect.right = 3 + 12 * 24, 3 + 24 * 24
        if self.rect.left < 3:
            self.life = False
        #    self.rect.left, self.rect.right = 3 + 12 * 24, 3 + 24 * 24
        if self.rect.right > 630 - 3:
            self.life = False
        #    self.rect.left, self.rect.right = 3 + 12 * 24, 3 + 24 * 24
        
        # 碰撞 brickGroup
        #if pygame.sprite.spritecollide(self, brickGroup, True, None):
        #    self.life = False
        #    moving = 0
        # 碰撞 ironGroup
        #if self.strong:
        #    if pygame.sprite.spritecollide(self, ironGroup, True, None):
        #        self.life = False
        #else:    
        #    if pygame.sprite.spritecollide(self, ironGroup, False, None):
        #        self.life = False
        #    moving = 0
        #return moving
        


Rule5_brickImage = pbinPath+"\\Z9\\rule5_file\\brick.png"
Rule5_ironImage = pbinPath+"\\Z9\\rule5_file\\iron.png"

class Rule5_Brick(pygame.sprite.Sprite):
    def __init__(self):
        pygame.sprite.Sprite.__init__(self)
        
        self.image = pygame.image.load(Rule5_brickImage)
        self.rect = self.image.get_rect()
        
class Rule5_Iron(pygame.sprite.Sprite):
    def __init__(self):
        pygame.sprite.Sprite.__init__(self)
        
        self.image = pygame.image.load(Rule5_ironImage)
        self.rect = self.image.get_rect()
        
class Rule5_Map():
    def __init__(self):
        self.brickGroup = pygame.sprite.Group()
        self.ironGroup  = pygame.sprite.Group()
        
        # 数字代表地图中的位置
        # 画砖块
        X1379 = [2, 3, 6, 7, 18, 19, 22, 23]
        Y1379 = [2, 3, 4, 5, 6, 7, 8, 9, 10, 17, 18, 19, 20, 21, 22, 23]
        X28 = [10, 11, 14, 15]
        Y28 = [2, 3, 4, 5, 6, 7, 8, 11, 12, 15, 16, 17, 18, 19, 20]
        X46 = [4, 5, 6, 7, 18, 19, 20, 21]
        Y46 = [13, 14]
        X5  = [12, 13]
        Y5  = [16, 17]
        X0Y0 = [(11,23),(12,23),(13,23),(14,23),(11,24),(14,24),(11,25),(14,25)]
        for x in X1379:
            for y in Y1379:
                self.brick = Rule5_Brick()
                self.brick.rect.left, self.brick.rect.top = 3 + x * 24, 3 + y * 24
                self.brickGroup.add(self.brick)
        for x in X28:
            for y in Y28:
                self.brick = Rule5_Brick()
                self.brick.rect.left, self.brick.rect.top = 3 + x * 24, 3 + y * 24
                self.brickGroup.add(self.brick)
        for x in X46:
            for y in Y46:
                self.brick = Rule5_Brick()
                self.brick.rect.left, self.brick.rect.top = 3 + x * 24, 3 + y * 24
                self.brickGroup.add(self.brick)
        for x in X5:
            for y in Y5:
                self.brick = Rule5_Brick()
                self.brick.rect.left, self.brick.rect.top = 3 + x * 24, 3 + y * 24
                self.brickGroup.add(self.brick)
        for x, y in X0Y0:
            self.brick = Rule5_Brick()
            self.brick.rect.left, self.brick.rect.top = 3 + x * 24, 3 + y * 24
            self.brickGroup.add(self.brick)
        
        # 画石头
        for x, y in [(0,14),(1,14),(12,6),(13,6),(12,7),(13,7),(24,14),(25,14)]:
            self.iron = Rule5_Iron()
            self.iron.rect.left, self.iron.rect.top = 3 + x * 24, 3 + y * 24
            self.ironGroup.add(self.iron)

tank_T1_0 = pbinPath+"\\Z9\\rule5_file\\tank_T1_0.png"
tank_T1_1 = pbinPath+"\\Z9\\rule5_file\\tank_T1_1.png"
tank_T1_2 = pbinPath+"\\Z9\\rule5_file\\tank_T1_2.png"
tank_T2_0 = pbinPath+"\\Z9\\rule5_file\\tank_T2_0.png"
tank_T2_1 = pbinPath+"\\Z9\\rule5_file\\tank_T2_1.png"
tank_T2_2 = pbinPath+"\\Z9\\rule5_file\\tank_T2_2.png"

class Rule5_MyTank(pygame.sprite.Sprite):
    def __init__(self, playerNumber):
        pygame.sprite.Sprite.__init__(self)
        
        # 玩家生命
        self.life = True
        
        #　第几个玩家   坦克的三个等级
        if playerNumber == 1:
            self.tank_L0_image = pygame.image.load(tank_T1_0).convert_alpha()
            self.tank_L1_image = pygame.image.load(tank_T1_1).convert_alpha()
            self.tank_L2_image = pygame.image.load(tank_T1_2).convert_alpha()
        if playerNumber == 2:
            self.tank_L0_image = pygame.image.load(tank_T2_0).convert_alpha()
            self.tank_L1_image = pygame.image.load(tank_T2_1).convert_alpha()
            self.tank_L2_image = pygame.image.load(tank_T2_2).convert_alpha()
        self.level = 0
        
        # 初始坦克为0级
        self.tank = self.tank_L0_image
        
        # 运动中的两种图片
        self.tank_R0 = self.tank.subsurface((0, 0),(48, 48))
        self.tank_R1 = self.tank.subsurface((48, 0),(48, 48))
        self.rect = self.tank_R0.get_rect()
        if playerNumber == 1:
            self.rect.left, self.rect.top = 3 + 24 * 8, 3 + 24 * 24 
        if playerNumber == 2:
            self.rect.left, self.rect.top = 3 + 24 * 16, 3 + 24 * 24 
        
        # 坦克速度   坦克方向   坦克生命   子弹冷却
        self.speed = 3
        self.dir_x, self.dir_y = 0, -1
        self.life = 3
        self.bulletNotCooling = True
        self.bullet = Rule5_Bullet()
        #self.bullet.rect.left, self.bullet.rect.right = 3 + 12 * 24, 3 + 24 * 24
    
    def shoot(self):
        # 子弹
        self.bullet.life = True
        self.bullet.changeImage(self.dir_x, self.dir_y)
        
        if self.dir_x == 0 and self.dir_y == -1:
            self.bullet.rect.left = self.rect.left + 20
            self.bullet.rect.bottom = self.rect.top + 1
        elif self.dir_x == 0 and self.dir_y == 1:
            self.bullet.rect.left = self.rect.left + 20
            self.bullet.rect.top = self.rect.bottom - 1
        elif self.dir_x == -1 and self.dir_y == 0:
            self.bullet.rect.right = self.rect.left - 1
            self.bullet.rect.top = self.rect.top + 20
        elif self.dir_x == 1 and self.dir_y == 0:
            self.bullet.rect.left = self.rect.right + 1
            self.bullet.rect.top = self.rect.top + 20
        
        if self.level == 1:
            self.bullet.speed  = 16
            self.bullet.strong = False
        if self.level == 2:
            self.bullet.speed  = 16
            self.bullet.strong = True
        if self.level == 3:
            self.bullet.speed  = 48
            self.bullet.strong = True
        
    
    def levelUp(self):
        if self.level < 2:
            self.level += 1
        if self.level == 0:
            self.tank = self.tank_L0_image
        if self.level == 1:
            self.tank = self.tank_L1_image
        if self.level == 2:
            self.tank = self.tank_L2_image
        if self.level == 3:
            self.tank = self.tank_L2_image
            
    def levelDown(self):
        if self.level > 0:
            self.level -= 1
        if self.level == 0:
            self.tank = self.tank_L0_image
            self.bullet.speed  = 6
            self.bullet.strong = False
        if self.level == 1:
            self.tank = self.tank_L1_image
        if self.level == 2:
            self.tank = self.tank_L2_image
        
        
    # 返回True 代表发生碰撞
    def moveUp(self, tankGroup, brickGroup, ironGroup):
        self.rect = self.rect.move(self.speed * 0, self.speed * -1)
        self.tank_R0 = self.tank.subsurface((0, 0),(48, 48))
        self.tank_R1 = self.tank.subsurface((48, 0),(48, 48))
        self.dir_x, self.dir_y = 0, -1
        if self.rect.top < 3:
            self.rect = self.rect.move(self.speed * 0, self.speed * 1)
            return True
        if pygame.sprite.spritecollide(self, brickGroup, False, None) \
            or pygame.sprite.spritecollide(self, ironGroup, False, None):
            self.rect = self.rect.move(self.speed * 0, self.speed * 1)
            return True
        if pygame.sprite.spritecollide(self, tankGroup, False, None):
            self.rect = self.rect.move(self.speed * 0, self.speed * 1)
            return True
        return False
    def moveDown(self, tankGroup, brickGroup, ironGroup):
        self.rect = self.rect.move(self.speed * 0, self.speed * 1)
        self.tank_R0 = self.tank.subsurface((0, 48),(48, 48))
        self.tank_R1 = self.tank.subsurface((48, 48),(48, 48))
        self.dir_x, self.dir_y = 0, 1
        if self.rect.bottom > 630 - 3:
            self.rect = self.rect.move(self.speed * 0, self.speed * -1)
            return True
        if pygame.sprite.spritecollide(self, brickGroup, False, None) \
            or pygame.sprite.spritecollide(self, ironGroup, False, None):
            self.rect = self.rect.move(self.speed * 0, self.speed * -1)
            return True
        if pygame.sprite.spritecollide(self, tankGroup, False, None):
            self.rect = self.rect.move(self.speed * 0, self.speed * -1)
            return True
        return False
    def moveLeft(self, tankGroup, brickGroup, ironGroup):
        self.rect = self.rect.move(self.speed * -1, self.speed * 0)
        self.tank_R0 = self.tank.subsurface((0, 96),(48, 48))
        self.tank_R1 = self.tank.subsurface((48, 96),(48, 48))
        self.dir_x, self.dir_y = -1, 0
        if self.rect.left < 3:
            self.rect = self.rect.move(self.speed * 1, self.speed * 0)
            return True
        if pygame.sprite.spritecollide(self, brickGroup, False, None) \
            or pygame.sprite.spritecollide(self, ironGroup, False, None):
            self.rect = self.rect.move(self.speed * 1, self.speed * 0)
            return True
        if pygame.sprite.spritecollide(self, tankGroup, False, None):
            self.rect = self.rect.move(self.speed * 1, self.speed * 0)
            return True
        return False
    def moveRight(self, tankGroup, brickGroup, ironGroup):
        self.rect = self.rect.move(self.speed * 1, self.speed * 0)
        self.tank_R0 = self.tank.subsurface((0, 144),(48, 48))
        self.tank_R1 = self.tank.subsurface((48, 144),(48, 48))
        self.dir_x, self.dir_y = 1, 0
        if self.rect.right > 630 - 3:
            self.rect = self.rect.move(self.speed * -1, self.speed * 0)
            return True
        if pygame.sprite.spritecollide(self, brickGroup, False, None) \
            or pygame.sprite.spritecollide(self, ironGroup, False, None):
            self.rect = self.rect.move(self.speed * -1, self.speed * 0)
            return True
        if pygame.sprite.spritecollide(self, tankGroup, False, None):
            self.rect = self.rect.move(self.speed * -1, self.speed * 0)
            return True
        return False
        


class Rule5_EnemyTank(pygame.sprite.Sprite):
    def __init__(self, x = None, kind = None, isred = None):
        pygame.sprite.Sprite.__init__(self)
        
        # 坦克出现前动画是否播放
        self.flash = False
        self.times = 90
        
        # 参数:坦克种类      
        self.kind = kind
        if not kind:
            self.kind = random.choice([1, 2, 3, 4])     
            
        # 选择敌军坦克种类        
        if self.kind == 1:
            self.enemy_x_0 = pygame.image.load(pbinPath+"\\Z9\\rule5_file\\enemy_1_0.png").convert_alpha()
            self.enemy_x_3 = pygame.image.load(pbinPath+"\\Z9\\rule5_file\\enemy_1_3.png").convert_alpha()
        if self.kind == 2:
            self.enemy_x_0 = pygame.image.load(pbinPath+"\\Z9\\rule5_file\\enemy_2_0.png").convert_alpha()
            self.enemy_x_3 = pygame.image.load(pbinPath+"\\Z9\\rule5_file\\enemy_2_3.png").convert_alpha()
        if self.kind == 3:
            self.enemy_x_0 = pygame.image.load(pbinPath+"\\Z9\\rule5_file\\enemy_3_1.png").convert_alpha()
            self.enemy_x_3 = pygame.image.load(pbinPath+"\\Z9\\rule5_file\\enemy_3_0.png").convert_alpha()
        if self.kind == 4:
            self.enemy_x_0 = pygame.image.load(pbinPath+"\\Z9\\rule5_file\\enemy_4_0.png").convert_alpha()
            self.enemy_x_3 = pygame.image.load(pbinPath+"\\Z9\\rule5_file\\enemy_4_3.png").convert_alpha()
        self.enemy_3_0 = pygame.image.load(pbinPath+"\\Z9\\rule5_file\\enemy_3_0.png").convert_alpha()
        self.enemy_3_2 = pygame.image.load(pbinPath+"\\Z9\\rule5_file\\enemy_3_2.png").convert_alpha()
        
        
        # 参数:是否携带食物
        self.isred = isred
        if not None:
            self.isred = random.choice((True, False, False, False, False))
        if self.isred:
            self.tank = self.enemy_x_3
        else:
            self.tank = self.enemy_x_0
        # 参数:坦克位置
        self.x = x
        if not self.x:
            self.x = random.choice([1, 2, 3])
        self.x -= 1
        
        # 运动中的两种图片
        self.tank_R0 = self.tank.subsurface(( 0, 48), (48, 48))
        self.tank_R1 = self.tank.subsurface((48, 48), (48, 48))
        self.rect = self.tank_R0.get_rect()
        self.rect.left, self.rect.top = 3 + self.x * 12 * 24, 3 + 0 * 24
        
        # 坦克速度   方向   生命   子弹生命   子弹延迟
        self.speed = 1
        self.dir_x, self.dir_y = 0, 1
        self.life = 1
        self.bulletNotCooling = True
        self.bullet = Rule5_Bullet()
        # 是否撞墙，撞墙则改变方向
        self.dirChange = False
        
        # 每种坦克不同的属性
        if self.kind == 2:
            self.speed = 3
        if self.kind == 3:
            self.life = 3
        
    def shoot(self):
        # 赋予子弹生命
        self.bullet.life = True
        self.bullet.changeImage(self.dir_x, self.dir_y)
        
        if self.dir_x == 0 and self.dir_y == -1:
            self.bullet.rect.left = self.rect.left + 20
            self.bullet.rect.bottom = self.rect.top + 1
        elif self.dir_x == 0 and self.dir_y == 1:
            self.bullet.rect.left = self.rect.left + 20
            self.bullet.rect.top = self.rect.bottom - 1
        elif self.dir_x == -1 and self.dir_y == 0:
            self.bullet.rect.right = self.rect.left - 1
            self.bullet.rect.top = self.rect.top + 20
        elif self.dir_x == 1 and self.dir_y == 0:
            self.bullet.rect.left = self.rect.right + 1
            self.bullet.rect.top = self.rect.top + 20
    
    def move(self, tankGroup, brickGroup, ironGroup):
        self.rect = self.rect.move(self.speed * self.dir_x, self.speed * self.dir_y)
        
        if self.dir_x == 0 and self.dir_y == -1:
            self.tank_R0 = self.tank.subsurface(( 0, 0),(48, 48))
            self.tank_R1 = self.tank.subsurface((48, 0),(48, 48))
        elif self.dir_x == 0 and self.dir_y == 1:
            self.tank_R0 = self.tank.subsurface(( 0, 48),(48, 48))
            self.tank_R1 = self.tank.subsurface((48, 48),(48, 48))
        elif self.dir_x == -1 and self.dir_y == 0:
            self.tank_R0 = self.tank.subsurface(( 0, 96),(48, 48))
            self.tank_R1 = self.tank.subsurface((48, 96),(48, 48))
        elif self.dir_x == 1 and self.dir_y == 0:
            self.tank_R0 = self.tank.subsurface(( 0, 144),(48, 48))
            self.tank_R1 = self.tank.subsurface((48, 144),(48, 48))
        
        
        # 碰撞地图边缘
        if self.rect.top < 3:
            self.rect = self.rect.move(self.speed * 0, self.speed * 1)
            self.dir_x, self.dir_y = random.choice(([0,1],[0,-1],[1,0],[-1,0]))
        elif self.rect.bottom > 630 - 3:
            self.rect = self.rect.move(self.speed * 0, self.speed * -1)
            self.dir_x, self.dir_y = random.choice(([0,1],[0,-1],[1,0],[-1,0]))
        elif self.rect.left < 3:
            self.rect = self.rect.move(self.speed * 1, self.speed * 0)
            self.dir_x, self.dir_y = random.choice(([0,1],[0,-1],[1,0],[-1,0]))
        elif self.rect.right > 630 - 3:
            self.rect = self.rect.move(self.speed * -1, self.speed * 0)
            self.dir_x, self.dir_y = random.choice(([0,1],[0,-1],[1,0],[-1,0]))
        # 碰撞墙体 和坦克
        if pygame.sprite.spritecollide(self, brickGroup, False, None) \
            or pygame.sprite.spritecollide(self, ironGroup, False, None) \
            or pygame.sprite.spritecollide(self, tankGroup, False, None):
            self.rect = self.rect.move(self.speed * -self.dir_x, self.speed * -self.dir_y)
            self.dir_x, self.dir_y = random.choice(([0,1],[0,-1],[1,0],[-1,0]))


Rule5_brickImage          = pbinPath+"\\Z9\\rule5_file\\brick.png"
Rule5_ironImage           = pbinPath+"\\Z9\\rule5_file\\iron.png"


class Rule5_Food(pygame.sprite.Sprite):
    def __init__(self):
        
        self.food_boom    = pygame.image.load(pbinPath+"\\Z9\\rule5_file\\food_boom.png").convert_alpha()
        self.food_clock   = pygame.image.load(pbinPath+"\\Z9\\rule5_file\\food_clock.png").convert_alpha()
        self.food_gun     = pygame.image.load(pbinPath+"\\Z9\\rule5_file\\food_gun.png").convert_alpha()
        self.food_iron    = pygame.image.load(pbinPath+"\\Z9\\rule5_file\\food_iron.png").convert_alpha()
        self.food_protect = pygame.image.load(pbinPath+"\\Z9\\rule5_file\\food_protect.png").convert_alpha()
        self.food_star    = pygame.image.load(pbinPath+"\\Z9\\rule5_file\\food_star.png").convert_alpha()
        self.food_tank    = pygame.image.load(pbinPath+"\\Z9\\rule5_file\\food_tank.png").convert_alpha()     
        self.kind = random.choice([1, 2, 3, 4, 5, 6, 7])
        if self.kind == 1:
            self.image = self.food_boom
        elif self.kind == 2:
            self.image = self.food_clock
        elif self.kind == 3:
            self.image = self.food_gun
        elif self.kind == 4:
            self.image = self.food_iron
        elif self.kind == 5:
            self.image = self.food_protect
        elif self.kind == 6:
            self.image = self.food_star
        elif self.kind == 7:
            self.image = self.food_tank
            
        self.rect = self.image.get_rect()
        self.rect.left = self.rect.top = random.randint(100, 500)
        
        self.life = False
        
    def change(self):
        self.kind = random.choice([1, 2, 3, 4, 5, 6, 7])
        if self.kind == 1:
            self.image = self.food_boom
        elif self.kind == 2:
            self.image = self.food_clock
        elif self.kind == 3:
            self.image = self.food_gun
        elif self.kind == 4:
            self.image = self.food_iron
        elif self.kind == 5:
            self.image = self.food_protect
        elif self.kind == 6:
            self.image = self.food_star
        elif self.kind == 7:
            self.image = self.food_tank
            
        self.rect.left = self.rect.top = random.randint(100, 500)
        self.life = True
        
            
            
class Rule5_Brick(pygame.sprite.Sprite):
    def __init__(self):
        pygame.sprite.Sprite.__init__(self)
        
        self.image = pygame.image.load(Rule5_brickImage)
        self.rect = self.image.get_rect()
        
class Rule5_Iron(pygame.sprite.Sprite):
    def __init__(self):
        pygame.sprite.Sprite.__init__(self)
        
        self.image = pygame.image.load(Rule5_ironImage)
        self.rect = self.image.get_rect()

  
class Rule5_Map():
    def __init__(self):
        self.brickGroup = pygame.sprite.Group()
        self.ironGroup  = pygame.sprite.Group()
        
        # 数字代表地图中的位置
        # 画砖块
        X1379 = [2, 3, 6, 7, 18, 19, 22, 23]
        Y1379 = [2, 3, 4, 5, 6, 7, 8, 9, 10, 17, 18, 19, 20, 21, 22, 23]
        X28 = [10, 11, 14, 15]
        Y28 = [2, 3, 4, 5, 6, 7, 8, 11, 12, 15, 16, 17, 18, 19, 20]
        X46 = [4, 5, 6, 7, 18, 19, 20, 21]
        Y46 = [13, 14]
        X5  = [12, 13]
        Y5  = [16, 17]
        X0Y0 = [(11,23),(12,23),(13,23),(14,23),(11,24),(14,24),(11,25),(14,25)]
        for x in X1379:
            for y in Y1379:
                self.brick = Rule5_Brick()
                self.brick.rect.left, self.brick.rect.top = 3 + x * 24, 3 + y * 24
                self.brickGroup.add(self.brick)
        for x in X28:
            for y in Y28:
                self.brick = Rule5_Brick()
                self.brick.rect.left, self.brick.rect.top = 3 + x * 24, 3 + y * 24
                self.brickGroup.add(self.brick)
        for x in X46:
            for y in Y46:
                self.brick = Rule5_Brick()
                self.brick.rect.left, self.brick.rect.top = 3 + x * 24, 3 + y * 24
                self.brickGroup.add(self.brick)
        for x in X5:
            for y in Y5:
                self.brick = Rule5_Brick()
                self.brick.rect.left, self.brick.rect.top = 3 + x * 24, 3 + y * 24
                self.brickGroup.add(self.brick)
        for x, y in X0Y0:
            self.brick = Rule5_Brick()
            self.brick.rect.left, self.brick.rect.top = 3 + x * 24, 3 + y * 24
            self.brickGroup.add(self.brick)
        
        # 画石头
        for x, y in [(0,14),(1,14),(12,6),(13,6),(12,7),(13,7),(24,14),(25,14)]:
            self.iron = Rule5_Iron()
            self.iron.rect.left, self.iron.rect.top = 3 + x * 24, 3 + y * 24
            self.ironGroup.add(self.iron)
            
        
        
def rule5_main():
    pygame.init()
    pygame.mixer.init()
    
    resolution = 630, 630
    screen = pygame.display.set_mode(resolution)
    pygame.display.set_caption("Tank War ")
    
    # 加载图片,音乐,音效.
    background_image     = pygame.image.load(pbinPath+"\\Z9\\rule5_file\\background.png")
    home_image           = pygame.image.load(pbinPath+"\\Z9\\rule5_file\\home.png")
    home_destroyed_image = pygame.image.load(pbinPath+"\\Z9\\rule5_file\\home_destroyed.png")
    
    bang_sound          = pygame.mixer.Sound(pbinPath+"\\Z9\\rule5_file\\bang.wav")
    bang_sound.set_volume(1)
    fire_sound           = pygame.mixer.Sound(pbinPath+"\\Z9\\rule5_file\\Gunfire.wav")
    start_sound          = pygame.mixer.Sound(pbinPath+"\\Z9\\rule5_file\\start.wav")
    start_sound.play()
    
    # 定义精灵组:坦克，我方坦克，敌方坦克，敌方子弹
    allTankGroup     = pygame.sprite.Group()
    mytankGroup      = pygame.sprite.Group()
    allEnemyGroup    = pygame.sprite.Group()
    redEnemyGroup    = pygame.sprite.Group()
    greenEnemyGroup  = pygame.sprite.Group()
    otherEnemyGroup  = pygame.sprite.Group()  
    enemyBulletGroup = pygame.sprite.Group()
    # 创建地图 
    bgRule5_Map = Rule5_Map()
    # 创建食物/道具 但不显示
    prop = Rule5_Food()
    # 创建我方坦克
    myTank_T1 =Rule5_MyTank(1)
    allTankGroup.add(myTank_T1)
    mytankGroup.add(myTank_T1)
    myTank_T2 =Rule5_MyTank(2)
    allTankGroup.add(myTank_T2)
    mytankGroup.add(myTank_T2)
    # 创建敌方 坦克
    for i in range(1, 4):
            enemy = Rule5_EnemyTank(i)
            allTankGroup.add(enemy)
            allEnemyGroup.add(enemy)
            if enemy.isred == True:
                redEnemyGroup.add(enemy)
                continue
            if enemy.kind == 3:
                greenEnemyGroup.add(enemy)
                continue
            otherEnemyGroup.add(enemy)
    # 敌军坦克出现动画
    appearance_image = pygame.image.load(pbinPath+"\\Z9\\rule5_file\\appear.png").convert_alpha()
    appearance = []
    appearance.append(appearance_image.subsurface(( 0, 0), (48, 48)))
    appearance.append(appearance_image.subsurface((48, 0), (48, 48)))
    appearance.append(appearance_image.subsurface((96, 0), (48, 48)))
    
    
    
    
    # 自定义事件
    # 创建敌方坦克延迟200
    DELAYEVENT = pygame.constants.USEREVENT
    pygame.time.set_timer(DELAYEVENT, 200)
    # 创建 敌方 子弹延迟1000
    ENEMYBULLETNOTCOOLINGEVENT = pygame.constants.USEREVENT + 1
    pygame.time.set_timer(ENEMYBULLETNOTCOOLINGEVENT, 1000)
    # 创建 我方 子弹延迟200
    MYBULLETNOTCOOLINGEVENT = pygame.constants.USEREVENT + 2
    pygame.time.set_timer(MYBULLETNOTCOOLINGEVENT, 200)
    # 敌方坦克 静止8000
    NOTMOVEEVENT = pygame.constants.USEREVENT + 3
    pygame.time.set_timer(NOTMOVEEVENT, 8000)
    
    
    delay = 100
    moving = 0
    movdir = 0
    moving2 = 0
    movdir2 = 0
    enemyNumber = 3
    enemyCouldMove      = True
    switch_R1_R2_image  = True
    homeSurvive         = True
    running_T1          = True
    running_T2          = True
    clock = pygame.time.Clock()
    while True:
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                pygame.quit()
                sys.exit()
            
            # 我方子弹冷却事件
            if event.type == MYBULLETNOTCOOLINGEVENT:
                myTank_T1.bulletNotCooling = True
                
            # 敌方子弹冷却事件
            if event.type == ENEMYBULLETNOTCOOLINGEVENT:
                for each in allEnemyGroup:
                    each.bulletNotCooling = True
            
            # 敌方坦克静止事件
            if event.type == NOTMOVEEVENT:
                enemyCouldMove = True
            
            # 创建敌方坦克延迟
            if event.type == DELAYEVENT:
                if enemyNumber < 4:
                    enemy = Rule5_EnemyTank()
                    if pygame.sprite.spritecollide(enemy, allTankGroup, False, None):
                        break
                    allEnemyGroup.add(enemy)
                    allTankGroup.add(enemy)
                    enemyNumber += 1
                    if enemy.isred == True:
                        redEnemyGroup.add(enemy)
                    elif enemy.kind == 3:
                        greenEnemyGroup.add(enemy)
                    else:
                        otherEnemyGroup.add(enemy)
                                
            if event.type == pygame.KEYDOWN:
                if event.key == pygame.K_ESCAPE and pygame.KMOD_CTRL:
                    pygame.quit()
                    sys.exit()
            
                if event.key == pygame.K_e:
                    myTank_T1.levelUp()
                if event.key == pygame.K_q:
                    myTank_T1.levelDown()
                if event.key == pygame.K_3:
                    myTank_T1.levelUp()
                    myTank_T1.levelUp()
                    myTank_T1.level = 3
                if event.key == pygame.K_2:
                    if myTank_T1.speed == 3:
                        myTank_T1.speed = 24
                    else:
                        myTank_T1.speed = 3
                if event.key == pygame.K_1:
                    for x, y in [(11,23),(12,23),(13,23),(14,23),(11,24),(14,24),(11,25),(14,25)]:
                        bgRule5_Map.brick = Rule5_Brick()
                        bgRule5_Map.brick.rect.left, bgRule5_Map.brick.rect.top = 3 + x * 24, 3 + y * 24
                        bgRule5_Map.brickGroup.add(bgRule5_Map.brick)                
                if event.key == pygame.K_4:
                    for x, y in [(11,23),(12,23),(13,23),(14,23),(11,24),(14,24),(11,25),(14,25)]:
                        bgRule5_Map.iron = Rule5_Iron()
                        bgRule5_Map.iron.rect.left, bgRule5_Map.iron.rect.top = 3 + x * 24, 3 + y * 24
                        bgRule5_Map.ironGroup.add(bgRule5_Map.iron)                
                


        # 检查用户的键盘操作
        key_pressed = pygame.key.get_pressed()
        # 玩家一的移动操作
        if moving:
            moving -= 1
            if movdir == 0:
                allTankGroup.remove(myTank_T1)
                if myTank_T1.moveUp(allTankGroup, bgRule5_Map.brickGroup, bgRule5_Map.ironGroup):
                    moving += 1
                allTankGroup.add(myTank_T1)
                running_T1 = True
            if movdir == 1:
                allTankGroup.remove(myTank_T1)
                if myTank_T1.moveDown(allTankGroup, bgRule5_Map.brickGroup, bgRule5_Map.ironGroup):
                    moving += 1
                allTankGroup.add(myTank_T1)
                running_T1 = True
            if movdir == 2:
                allTankGroup.remove(myTank_T1)
                if myTank_T1.moveLeft(allTankGroup, bgRule5_Map.brickGroup, bgRule5_Map.ironGroup):
                    moving += 1
                allTankGroup.add(myTank_T1)
                running_T1 = True
            if movdir == 3:
                allTankGroup.remove(myTank_T1)
                if myTank_T1.moveRight(allTankGroup, bgRule5_Map.brickGroup, bgRule5_Map.ironGroup):
                    moving += 1
                allTankGroup.add(myTank_T1)
                running_T1 = True
                
        if not moving:
            if key_pressed[pygame.K_w]:
                moving = 7
                movdir = 0
                running_T1 = True
                allTankGroup.remove(myTank_T1)
                if myTank_T1.moveUp(allTankGroup, bgRule5_Map.brickGroup, bgRule5_Map.ironGroup):
                    moving = 0
                allTankGroup.add(myTank_T1)
            elif key_pressed[pygame.K_s]:
                moving = 7
                movdir = 1
                running_T1 = True
                allTankGroup.remove(myTank_T1)
                if myTank_T1.moveDown(allTankGroup, bgRule5_Map.brickGroup, bgRule5_Map.ironGroup):
                    moving = 0
                allTankGroup.add(myTank_T1)
            elif key_pressed[pygame.K_a]:
                moving = 7
                movdir = 2
                running_T1 = True
                allTankGroup.remove(myTank_T1)
                if myTank_T1.moveLeft(allTankGroup, bgRule5_Map.brickGroup, bgRule5_Map.ironGroup):
                    moving = 0
                allTankGroup.add(myTank_T1)
            elif key_pressed[pygame.K_d]:
                moving = 7
                movdir = 3
                running_T1 = True
                allTankGroup.remove(myTank_T1)
                if myTank_T1.moveRight(allTankGroup, bgRule5_Map.brickGroup, bgRule5_Map.ironGroup):
                    moving = 0
                allTankGroup.add(myTank_T1)
        if key_pressed[pygame.K_j]:
            if not myTank_T1.bullet.life and myTank_T1.bulletNotCooling:
                fire_sound.play()
                myTank_T1.shoot()
                myTank_T1.bulletNotCooling = False
        if key_pressed[pygame.K_q]:
            if not myTank_T1.bullet.life and myTank_T1.bulletNotCooling:
                fire_sound.play()
                myTank_T1.shoot()
                myTank_T1.bulletNotCooling = False
        if key_pressed[pygame.K_e]:
            if not myTank_T1.bullet.life and myTank_T1.bulletNotCooling:
                fire_sound.play()
                myTank_T1.shoot()
                myTank_T1.bulletNotCooling = False 
        # 玩家二的移动操作
        if moving2:
            moving2 -= 1
            if movdir2 == 0:
                allTankGroup.remove(myTank_T2)
                myTank_T2.moveUp(allTankGroup, bgRule5_Map.brickGroup, bgRule5_Map.ironGroup)
                allTankGroup.add(myTank_T2)
                running_T2 = True
            if movdir2 == 1:
                allTankGroup.remove(myTank_T2)
                myTank_T2.moveDown(allTankGroup, bgRule5_Map.brickGroup, bgRule5_Map.ironGroup)
                allTankGroup.add(myTank_T2)
                running_T2 = True
            if movdir2 == 2:
                allTankGroup.remove(myTank_T2)
                myTank_T2.moveLeft(allTankGroup, bgRule5_Map.brickGroup, bgRule5_Map.ironGroup)
                allTankGroup.add(myTank_T2)
                running_T2 = True
            if movdir2 == 3:
                allTankGroup.remove(myTank_T2)
                myTank_T2.moveRight(allTankGroup, bgRule5_Map.brickGroup, bgRule5_Map.ironGroup)
                allTankGroup.add(myTank_T2)
                running_T2 = True
                
        if not moving2:
            if key_pressed[pygame.K_UP]:
                allTankGroup.remove(myTank_T2)
                myTank_T2.moveUp(allTankGroup, bgRule5_Map.brickGroup, bgRule5_Map.ironGroup)
                allTankGroup.add(myTank_T2)
                moving2 = 7
                movdir2 = 0
                running_T2 = True
            elif key_pressed[pygame.K_DOWN]:
                allTankGroup.remove(myTank_T2)
                myTank_T2.moveDown(allTankGroup, bgRule5_Map.brickGroup, bgRule5_Map.ironGroup)
                allTankGroup.add(myTank_T2)
                moving2 = 7
                movdir2 = 1
                running_T2 = True
            elif key_pressed[pygame.K_LEFT]:
                allTankGroup.remove(myTank_T2)
                myTank_T2.moveLeft(allTankGroup, bgRule5_Map.brickGroup, bgRule5_Map.ironGroup)
                allTankGroup.add(myTank_T2)
                moving2 = 7
                movdir2 = 2
                running_T2 = True
            elif key_pressed[pygame.K_RIGHT]:
                allTankGroup.remove(myTank_T2)
                myTank_T2.moveRight(allTankGroup, bgRule5_Map.brickGroup, bgRule5_Map.ironGroup)
                allTankGroup.add(myTank_T2)
                moving2 = 7
                movdir2 = 3
                running_T2 = True
        if key_pressed[pygame.K_PAGEUP]:
            if not myTank_T2.bullet.life:
                fire_sound.play()
                myTank_T2.shoot()

        if key_pressed[pygame.K_PAGEDOWN]:
            if not myTank_T2.bullet.life:
                fire_sound.play()
                myTank_T2.shoot()
        
        
        
        # 画背景
        screen.blit(background_image, (0, 0))
        # 画砖块
        for each in bgRule5_Map.brickGroup:
            screen.blit(each.image, each.rect)        
        # 花石头
        for each in bgRule5_Map.ironGroup:
            screen.blit(each.image, each.rect)        
        # 画home
        if homeSurvive:
            screen.blit(home_image, (3 + 12 * 24, 3 + 24 * 24))
        else:
            screen.blit(home_destroyed_image, (3 + 12 * 24, 3 + 24 * 24))
        # 画我方坦克1
        if not (delay % 5):
            switch_R1_R2_image = not switch_R1_R2_image
        if switch_R1_R2_image and running_T1:
            screen.blit(myTank_T1.tank_R0, (myTank_T1.rect.left, myTank_T1.rect.top))
            running_T1 = False
        else:
            screen.blit(myTank_T1.tank_R1, (myTank_T1.rect.left, myTank_T1.rect.top))
        # 画我方坦克2
        if switch_R1_R2_image and running_T2:
            screen.blit(myTank_T2.tank_R0, (myTank_T2.rect.left, myTank_T2.rect.top))
            running_T2 = False
        else:
            screen.blit(myTank_T2.tank_R1, (myTank_T2.rect.left, myTank_T2.rect.top))    
        # 画敌方坦克
        for each in allEnemyGroup:
            # 判断5毛钱特效是否播放            
            if each.flash:
                #　判断画左动作还是右动作
                if switch_R1_R2_image:
                    screen.blit(each.tank_R0, (each.rect.left, each.rect.top))
                    if enemyCouldMove:
                        allTankGroup.remove(each)
                        each.move(allTankGroup, bgRule5_Map.brickGroup, bgRule5_Map.ironGroup)
                        allTankGroup.add(each)
                else:
                    screen.blit(each.tank_R1, (each.rect.left, each.rect.top))
                    if enemyCouldMove:
                        allTankGroup.remove(each)
                        each.move(allTankGroup, bgRule5_Map.brickGroup, bgRule5_Map.ironGroup)
                        allTankGroup.add(each)                    
            else:
                # 播放5毛钱特效
                if each.times > 0:
                    each.times -= 1
                    if each.times <= 10:
                        screen.blit(appearance[2], (3 + each.x * 12 * 24, 3))
                    elif each.times <= 20:
                        screen.blit(appearance[1], (3 + each.x * 12 * 24, 3))
                    elif each.times <= 30:
                        screen.blit(appearance[0], (3 + each.x * 12 * 24, 3))
                    elif each.times <= 40:
                        screen.blit(appearance[2], (3 + each.x * 12 * 24, 3))
                    elif each.times <= 50:
                        screen.blit(appearance[1], (3 + each.x * 12 * 24, 3))
                    elif each.times <= 60:
                        screen.blit(appearance[0], (3 + each.x * 12 * 24, 3))
                    elif each.times <= 70:
                        screen.blit(appearance[2], (3 + each.x * 12 * 24, 3))
                    elif each.times <= 80:
                        screen.blit(appearance[1], (3 + each.x * 12 * 24, 3))
                    elif each.times <= 90:
                        screen.blit(appearance[0], (3 + each.x * 12 * 24, 3))
                if each.times == 0:
                    each.flash = True
      
                 
        # 绘制我方子弹1
        if myTank_T1.bullet.life:
            myTank_T1.bullet.move()    
            screen.blit(myTank_T1.bullet.bullet, myTank_T1.bullet.rect)
            # 子弹 碰撞 子弹
            for each in enemyBulletGroup:
                if each.life:
                    if pygame.sprite.collide_rect(myTank_T1.bullet, each):
                        myTank_T1.bullet.life = False
                        each.life = False
                        pygame.sprite.spritecollide(myTank_T1.bullet, enemyBulletGroup, True, None)
            # 子弹 碰撞 敌方坦克
            if pygame.sprite.spritecollide(myTank_T1.bullet, redEnemyGroup, True, None):
                prop.change()
                bang_sound.play()
                enemyNumber -= 1
                myTank_T1.bullet.life = False
            elif pygame.sprite.spritecollide(myTank_T1.bullet,greenEnemyGroup, False, None):
                for each in greenEnemyGroup:
                    if pygame.sprite.collide_rect(myTank_T1.bullet, each):
                        if each.life == 1:
                            pygame.sprite.spritecollide(myTank_T1.bullet,greenEnemyGroup, True, None)
                            bang_sound.play()
                            enemyNumber -= 1
                        elif each.life == 2:
                            each.life -= 1
                            each.tank = each.enemy_3_0
                        elif each.life == 3:
                            each.life -= 1
                            each.tank = each.enemy_3_2
                myTank_T1.bullet.life = False
            elif pygame.sprite.spritecollide(myTank_T1.bullet, otherEnemyGroup, True, None):
                bang_sound.play()
                enemyNumber -= 1
                myTank_T1.bullet.life = False    
            #if pygame.sprite.spritecollide(myTank_T1.bullet, allEnemyGroup, True, None):
            #    bang_sound.play()
            #    enemyNumber -= 1
            #    myTank_T1.bullet.life = False
            # 子弹 碰撞 brickGroup
            if pygame.sprite.spritecollide(myTank_T1.bullet, bgRule5_Map.brickGroup, True, None):
                myTank_T1.bullet.life = False
                myTank_T1.bullet.rect.left, myTank_T1.bullet.rect.right = 3 + 12 * 24, 3 + 24 * 24
            # 子弹 碰撞 brickGroup
            if myTank_T1.bullet.strong:
                if pygame.sprite.spritecollide(myTank_T1.bullet, bgRule5_Map.ironGroup, True, None):
                    myTank_T1.bullet.life = False
                    myTank_T1.bullet.rect.left, myTank_T1.bullet.rect.right = 3 + 12 * 24, 3 + 24 * 24
            else:    
                if pygame.sprite.spritecollide(myTank_T1.bullet, bgRule5_Map.ironGroup, False, None):
                    myTank_T1.bullet.life = False
                    myTank_T1.bullet.rect.left, myTank_T1.bullet.rect.right = 3 + 12 * 24, 3 + 24 * 24
        
        # 绘制我方子弹2
        if myTank_T2.bullet.life:
            myTank_T2.bullet.move()    
            screen.blit(myTank_T2.bullet.bullet, myTank_T2.bullet.rect)
            # 子弹 碰撞 敌方坦克
            if pygame.sprite.spritecollide(myTank_T2.bullet, allEnemyGroup, True, None):
                bang_sound.play()
                enemyNumber -= 1
                myTank_T2.bullet.life = False
            # 子弹 碰撞 brickGroup
            if pygame.sprite.spritecollide(myTank_T2.bullet, bgRule5_Map.brickGroup, True, None):
                myTank_T2.bullet.life = False
                myTank_T2.bullet.rect.left, myTank_T2.bullet.rect.right = 3 + 12 * 24, 3 + 24 * 24
            # 子弹 碰撞 brickGroup
            if myTank_T2.bullet.strong:
                if pygame.sprite.spritecollide(myTank_T2.bullet, bgRule5_Map.ironGroup, True, None):
                    myTank_T2.bullet.life = False
                    myTank_T2.bullet.rect.left, myTank_T2.bullet.rect.right = 3 + 12 * 24, 3 + 24 * 24
            else:    
                if pygame.sprite.spritecollide(myTank_T2.bullet, bgRule5_Map.ironGroup, False, None):
                    myTank_T2.bullet.life = False
                    myTank_T2.bullet.rect.left, myTank_T2.bullet.rect.right = 3 + 12 * 24, 3 + 24 * 24
        

        # 绘制敌人子弹
        for each in allEnemyGroup:
            # 如果子弹没有生命，则赋予子弹生命
            if not each.bullet.life and each.bulletNotCooling and enemyCouldMove:
                enemyBulletGroup.remove(each.bullet)
                each.shoot()
                enemyBulletGroup.add(each.bullet)
                each.bulletNotCooling = False
            # 如果5毛钱特效播放完毕 并且 子弹存活 则绘制敌方子弹
            if each.flash:
                if each.bullet.life:
                    # 如果敌人可以移动
                    if enemyCouldMove:
                        each.bullet.move()
                    screen.blit(each.bullet.bullet, each.bullet.rect)
                    # 子弹 碰撞 我方坦克
                    if pygame.sprite.collide_rect(each.bullet, myTank_T1):
                        bang_sound.play()
                        myTank_T1.rect.left, myTank_T1.rect.top = 3 + 8 * 24, 3 + 24 * 24 
                        each.bullet.life = False
                        moving = 0  # 重置移动控制参数
                        for i in range(myTank_T1.level+1):
                            myTank_T1.levelDown()
                    if pygame.sprite.collide_rect(each.bullet, myTank_T2):
                        bang_sound.play()
                        myTank_T2.rect.left, myTank_T2.rect.top = 3 + 16 * 24, 3 + 24 * 24 
                        each.bullet.life = False
                    # 子弹 碰撞 brickGroup
                    if pygame.sprite.spritecollide(each.bullet, bgRule5_Map.brickGroup, True, None):
                        each.bullet.life = False
                    # 子弹 碰撞 ironGroup
                    if each.bullet.strong:
                        if pygame.sprite.spritecollide(each.bullet, bgRule5_Map.ironGroup, True, None):
                            each.bullet.life = False
                    else:    
                        if pygame.sprite.spritecollide(each.bullet, bgRule5_Map.ironGroup, False, None):
                            each.bullet.life = False
             
        # 最后画食物/道具
        if prop.life:
            screen.blit(prop.image, prop.rect)
            # 我方坦克碰撞 食物/道具
            if pygame.sprite.collide_rect(myTank_T1, prop):
                if prop.kind == 1:  # 敌人全毁
                    for each in allEnemyGroup:
                        if pygame.sprite.spritecollide(each, allEnemyGroup, True, None):
                            bang_sound.play()
                            enemyNumber -= 1
                    prop.life = False
                if prop.kind == 2:  # 敌人静止
                    enemyCouldMove = False
                    prop.life = False
                if prop.kind == 3:  # 子弹增强
                    myTank_T1.bullet.strong = True
                    prop.life = False
                if prop.kind == 4:  # 家得到保护
                    for x, y in [(11,23),(12,23),(13,23),(14,23),(11,24),(14,24),(11,25),(14,25)]:
                        bgRule5_Map.iron = Rule5_Iron()
                        bgRule5_Map.iron.rect.left, bgRule5_Map.iron.rect.top = 3 + x * 24, 3 + y * 24
                        bgRule5_Map.ironGroup.add(bgRule5_Map.iron)                
                    prop.life = False
                if prop.kind == 5:  # 坦克无敌
                    prop.life = False
                    pass
                if prop.kind == 6:  # 坦克升级
                    myTank_T1.levelUp()
                    prop.life = False
                if prop.kind == 7:  # 坦克生命+1
                    myTank_T1.life += 1
                    prop.life = False
                    
              
        # 延迟
        delay -= 1
        if not delay:
            delay = 100    
        
        pygame.display.flip()
        clock.tick(60)

class TankWar_Rule_5(Basic_Rule):

    def __init__(self, rule_index, operation_type):
        self.rule_index = rule_index
        self.operation_type = operation_type
        
        
    def initParamsWithInputList(self,inputParamList):
        return True
        
    def applyNoParamOperationRule0(self):
        try:
            global Rule5_brickImage
            global Rule5_ironImage
            global pbinPath
            global tank_T1_0
            global tank_T1_1
            global tank_T1_2
            global tank_T2_0
            global tank_T2_1
            global tank_T2_2
            Rule5_brickImage = pbinPath+"\\Z9\\rule5_file\\brick.png"
            Rule5_ironImage = pbinPath+"\\Z9\\rule5_file\\iron.png"
            tank_T1_0 = pbinPath+"\\Z9\\rule5_file\\tank_T1_0.png"
            tank_T1_1 = pbinPath+"\\Z9\\rule5_file\\tank_T1_1.png"
            tank_T1_2 = pbinPath+"\\Z9\\rule5_file\\tank_T1_2.png"
            tank_T2_0 = pbinPath+"\\Z9\\rule5_file\\tank_T2_0.png"
            tank_T2_1 = pbinPath+"\\Z9\\rule5_file\\tank_T2_1.png"
            tank_T2_2 = pbinPath+"\\Z9\\rule5_file\\tank_T2_2.png"

            rule5_main()
        except SystemExit:
            pass
        except:
            traceback.print_exc()
            pygame.quit()
            input()
        
    def simpleDesc(self):
        return "坦克大战(双人玩)_TankWar"
###################### Rule_5 End  ###################### 

###################### Rule_6 Begin  ######################


one_time_rule6 = 0.5 #时间流速
show_n_rule6 = 0
show_frequency_rule6 = 0.0015 #烟花绽放频率，数值越大频率越高
yanhua_count_rule6 = 30 # 烟花数量

class Yanhua():
    is_show = False
    x, y = 0, 0
    vy = 0
    p_list = []
    color = [0, 0, 0]
    v = 0

    def __init__(self, x, y, vy, n=300, color=[0, 255, 0], v=10):
        self.x = x
        self.y = y
        self.vy = vy
        self.color = color
        self.v = v
        # self.is_show = True
        for i in range(n):
            self.p_list.append([random.random() * 2 * math.pi, 0, v * math.pow(random.random(), 1 / 3)])

    def chongzhi(self,WINDOW_W,WINDOW_H):
        self.is_show = True
        self.x = random.randint(WINDOW_W // 2 - 350, WINDOW_W // 2 + 350)
        self.y = random.randint(int(WINDOW_H / 2), int(WINDOW_H * 3 / 5))
        self.vy = -40 * (random.random() * 0.4 + 0.8) - self.vy * 0.2
        color_r_rule5 = random.randint(0, 255)
        color_g_rule5 = random.randint(0, 255)
        color_b_rule5 = random.randint(0, 255)
        self.color = [color_r_rule5,color_g_rule5,color_b_rule5]
        n = len(self.p_list)
        self.p_list = []
        for i in range(n):
            self.p_list.append([random.random() * 2 * math.pi, 0, self.v * math.pow(random.random(), 1 / 3)])

    def run(self,WINDOW_H,one_time_rule6):
        global show_n_rule6
        for p in self.p_list:
            p[1] = p[1] + (random.random() * 0.6 + 0.7) * p[2]
            p[2] = p[2] * 0.97
            if p[2] < 1.2:
                self.color[0] *= 0.9999
                self.color[1] *= 0.9999
                self.color[2] *= 0.9999

            if max(self.color) < 10 or self.y>WINDOW_H+p[1]:
                show_n_rule6 -= 1
                self.is_show = False
                break
        self.vy += 10 * one_time_rule6
        self.y += self.vy * one_time_rule6



def rule6_main(yanhua_count):
    pygame.init()
    screenInfo = pygame.display.Info()
    PANEL_width = screenInfo.current_w
    PANEL_highly = screenInfo.current_h
    FONT_PX = 15
    global show_n_rule6
    screen = pygame.display.set_mode((PANEL_width, PANEL_highly))  # 全屏模式
    # global Device_Width
    # global Device_Height
    # screen = pygame.display.set_mode((Device_Width, Device_Height))  # 全屏模式
    font = pygame.font.SysFont("arial", 20)
    bg_suface = pygame.Surface((PANEL_width, PANEL_highly), flags=pygame.SRCALPHA)
    pygame.Surface.convert(bg_suface)
    bg_suface.fill(pygame.Color(0, 0, 0, 28))

    sound_wav_rule6 = pygame.mixer.music.load(pbinPath+"\\Z9\\rule6_file\\yanhua.mp3")  
    #  pygame.mixer.music.load('res/bg.mp3')
    pygame.mixer.music.set_volume(0.2)
    pygame.mixer.music.play()
    pygame.display.set_caption("烟花")
    print("rule6_main.yanhua_count:"+str(yanhua_count))
    # 加载图片,音乐,音效.
    background_image     = pygame.image.load(pbinPath+"\\Z9\\rule6_file\\background.jpg")
    screen.fill((0, 0, 0))
    screen.blit(background_image, (0, 0))
    yanhua_list = []
    for i in range(yanhua_count):
        color_r_rule6 = random.randint(0, 255)
        color_g_rule6 = random.randint(0, 255)
        color_b_rule6 = random.randint(0, 255)
        speed_rule6 =  random.randint(10, 30)
        yanhua_list.append(Yanhua(300, 300, -20, n=100, color=[color_r_rule6, color_g_rule6, color_b_rule6], v=speed_rule6))

    clock = pygame.time.Clock()
    # 游戏主循环
    while True:
        if not pygame.mixer.music.get_busy():
            pygame.mixer.music.play()
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                # 接收到退出时间后退出程序
                exit()
    
        # 将背景图画上去
        screen.fill((0, 0, 0))
        #screen.blit(background_image, (0, 0))
        # 放烟花
        for i, yh in enumerate(yanhua_list):
            if not yh.is_show:
                yh.is_show = False
                if random.random() < show_frequency_rule6 * (len(yanhua_list) - show_n_rule6):
                    show_n_rule6 += 1
                    yh.chongzhi(PANEL_width,PANEL_highly)
                continue
            yh.run(PANEL_highly,one_time_rule6)
            for p in yh.p_list:
                x, y = yh.x + p[1] * math.cos(p[0]), yh.y + p[1] * math.sin(p[0])
                if random.random() < 0.055:
                    screen.set_at((int(x), int(y)),(255,255,255))
                else:
                    screen.set_at((int(x), int(y)), (int(yh.color[0]), int(yh.color[1]), int(yh.color[2])))
    
        # 刷新画面
        pygame.display.update()
        # 返回上一个调用的时间（ms）
        time_passed = clock.tick(25)



class YanHua_Rule_6(Basic_Rule):

    def __init__(self, rule_index, operation_type):
        self.rule_index = rule_index
        self.operation_type = operation_type

#    def __init__(self, rule_index, operation_type,file_type):
#        self.rule_index = rule_index
#        self.operation_type = operation_type
#        self.file_type = file_type


    def applyNoParamOperationRule0(self):
        global yanhua_count_rule6
        rule6_main(yanhua_count_rule6);# 烟花的数量

    def simpleDesc(self):
        return "屏幕打印烟花"

###################### Rule_6 End  ######################

def initRule():
    realTypeRuleList.append(CodeRain_Rule_1(1,0));
    realTypeRuleList.append(PlaneWars_Rule_2(2,0));
    realTypeRuleList.append(AI_SnakeEatFood_Rule_3(3,0));
    realTypeRuleList.append(SnakeEatFood_Rule_4(4,0));
    realTypeRuleList.append(TankWar_Rule_5(5,0));
    realTypeRuleList.append(YanHua_Rule_6(6,0));
    
    print("当前规则数量:"+str(len(realTypeRuleList)))



def DoRuleOperation( mRuleObj ):
    if mRuleObj.initParamsWithInputList(params_list):
        print("参数初始化完成 Step1 完成 开始执行对应 Rule 逻辑")
        if mRuleObj.operation_type == 0:
            mRuleObj.applyNoParamOperationRule0()
        if mRuleObj.operation_type == 1:
            mRuleObj.applyStringOperationRule1()
        if mRuleObj.operation_type == 2:
            mRuleObj.applyFileByteOperationRule2()
        if mRuleObj.operation_type == 3:
            mRuleObj.applyFileListRule3()
        if mRuleObj.operation_type == 4:
            mRuleObj.applySubFileListRule4()
    else:
        print("参数初始化错误 程序在 Step1 终止, 请检查输入参数!")

def ShowAllRuleTip():
    print()
    print()
    print()
    print()
    print("════════════════"+" All Rule 【"+str(len(realTypeRuleList))+"】 Tip Begin "+"════════════════")
    for ruleobj in realTypeRuleList:
        ruleobj_tip = ruleobj.ruleTip()
        print(ruleobj_tip)
        print()
    print("════════════════"+" All Rule 【"+str(len(realTypeRuleList))+"】 Tip End "+"════════════════")


if __name__ == '__main__':
    initSystemInfo()
    initRule()
    CUR_Rule_Index_Int = int(CUR_Rule_Index)
    if CUR_Rule_Index_Int != -1 and CUR_Rule_Index_Int <= len(realTypeRuleList) :
        print("执行 对应的 CUR_Rule_Index="+CUR_Rule_Index+"的规则逻辑")
        RuleObj = realTypeRuleList[CUR_Rule_Index_Int-1]
        DoRuleOperation(RuleObj)
    else:
        print("当前索引搜索无效 请检查-打印Tip CUR_Rule_Index="+CUR_Rule_Index + "  规则总数:"+str(len(realTypeRuleList)))
        ShowAllRuleTip()