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

def initRule():
    realTypeRuleList.append(CodeRain_Rule_1(0,0));
    realTypeRuleList.append(PlaneWars_Rule_2(1,0));
    
    
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
    if CUR_Rule_Index_Int != -1 and CUR_Rule_Index_Int < len(realTypeRuleList) :
        print("执行 对应的 CUR_Rule_Index="+CUR_Rule_Index+"的规则逻辑")
        RuleObj = realTypeRuleList[CUR_Rule_Index_Int]
        DoRuleOperation(RuleObj)
    else:
        print("当前索引搜索无效 请检查-打印Tip CUR_Rule_Index="+CUR_Rule_Index + "  规则总数:"+str(len(realTypeRuleList)))
        ShowAllRuleTip()
