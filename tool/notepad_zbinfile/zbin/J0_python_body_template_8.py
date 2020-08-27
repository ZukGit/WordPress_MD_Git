#  《First_Define_Template_1》= 《# 【ZHoldPlace_NodeName】_zukgit_website  =   【ZHoldPlace_Node_Website】》
#  《First_Define_Template_1》= 《tree_node_name="【ZHoldPlace_NodeName】"+"_"》

#  《Method_Call_Template_1》= 《createexcel('【ZHoldPlace_pythonMethodName】_【ZHoldPlace_Year_Int】.xlsx')》
#  《Method_Call_Template_1》= 《【ZHoldPlace_pythonMethodName】_【ZHoldPlace_Year_Int】_book = load_workbook('【ZHoldPlace_J0_Dir_PATH】【ZHoldPlace_pythonMethodName】_【ZHoldPlace_Year_Int】.xlsx')》
#  《Method_Call_Template_1》= 《【ZHoldPlace_pythonMethodName】_【ZHoldPlace_Year_Int】_excel_writer = pd.ExcelWriter('【ZHoldPlace_J0_Dir_PATH】【ZHoldPlace_pythonMethodName】_【ZHoldPlace_Year_Int】.xlsx', engine='openpyxl')》
#  《Method_Call_Template_1》= 《【ZHoldPlace_pythonMethodName】_【ZHoldPlace_Year_Int】_excel_writer.book = 【ZHoldPlace_pythonMethodName】_【ZHoldPlace_Year_Int】_book》
#  《Method_Call_Template_1》= 《【ZHoldPlace_pythonMethodName】_【ZHoldPlace_Year_Int】_excel_writer.sheets = dict((ws.title, ws) for ws in 【ZHoldPlace_pythonMethodName】_【ZHoldPlace_Year_Int】_book.worksheets)》


#  《Method_Call_Template_2》= 《【ZHoldPlace_pythonMethodName】_【ZHoldPlace_Year_Int】_【ZHoldPlace_Month_Index】_xlsx_frame=pd.DataFrame()》
#  《Method_Call_Template_2》= 《if '【ZHoldPlace_Month_Index】' in 【ZHoldPlace_pythonMethodName】_【ZHoldPlace_Year_Int】_excel_writer.sheets:》
#  《Method_Call_Template_2》= 《    【ZHoldPlace_pythonMethodName】_【ZHoldPlace_Year_Int】_【ZHoldPlace_Month_Index】_xlsx_frame=pd.read_excel('【ZHoldPlace_J0_Dir_PATH】【ZHoldPlace_pythonMethodName】_【ZHoldPlace_Year_Int】.xlsx',sheet_name ='【ZHoldPlace_Month_Index】' , index=False)》

#  《Method_Call_Template_3》= 《J0_PROPS.put(tree_node_name+"record_date", "【ZHoldPlace_OPERATION_DAY】")       ###  更新 记录日期》
#  《Method_Call_Template_3》= 《【ZHoldPlace_pythonMethodName】_【ZHoldPlace_OPERATION_DAY】 = pro.【ZHoldPlace_pythonMethodName】(【ZHoldPlace_propKey2ValueList】, fields='【ZHoldPlace_fieldList】')》
#  《Method_Call_Template_3》= 《【ZHoldPlace_pythonMethodName】_【ZHoldPlace_OPERATION_DAY】_tscode_list = list() 》
#  《Method_Call_Template_3》= 《for ts_code_sh in 【ZHoldPlace_pythonMethodName】_【ZHoldPlace_OPERATION_DAY】['ts_code']:》
#  《Method_Call_Template_3》= 《    stock_name = tscode_name_dict.get(ts_code_sh)》
#  《Method_Call_Template_3》= 《    if stock_name is None:》
#  《Method_Call_Template_3》= 《        stock_name = 'null'》
#  《Method_Call_Template_3》= 《    【ZHoldPlace_pythonMethodName】_【ZHoldPlace_OPERATION_DAY】_tscode_list.append(stock_name)》
#  《Method_Call_Template_3》= 《【ZHoldPlace_pythonMethodName】_【ZHoldPlace_OPERATION_DAY】_addname_dataframe=pd.DataFrame()》
#  《Method_Call_Template_3》= 《【ZHoldPlace_pythonMethodName】_【ZHoldPlace_OPERATION_DAY】_addname_dataframe['cname'] = 【ZHoldPlace_pythonMethodName】_【ZHoldPlace_OPERATION_DAY】_tscode_list》
#  《Method_Call_Template_3》= 《for table_name in 【ZHoldPlace_pythonMethodName】_【ZHoldPlace_OPERATION_DAY】.columns.values.tolist():》
#  《Method_Call_Template_3》= 《    【ZHoldPlace_pythonMethodName】_【ZHoldPlace_OPERATION_DAY】_addname_dataframe[table_name] = 【ZHoldPlace_pythonMethodName】_【ZHoldPlace_OPERATION_DAY】[table_name]》
#  《Method_Call_Template_3》= 《print("【ZHoldPlace_pythonMethodName】_【ZHoldPlace_OPERATION_DAY】 返回数据 row 行数 = "+str(【ZHoldPlace_pythonMethodName】_【ZHoldPlace_OPERATION_DAY】.shape[0]))》 
#  《Method_Call_Template_3》= 《【ZHoldPlace_pythonMethodName】_【ZHoldPlace_Year_Int】_【ZHoldPlace_Month_Index】_xlsx_frame=【ZHoldPlace_pythonMethodName】_【ZHoldPlace_Year_Int】_【ZHoldPlace_Month_Index】_xlsx_frame.append(【ZHoldPlace_pythonMethodName】_【ZHoldPlace_OPERATION_DAY】_addname_dataframe,ignore_index=True)》
#  《Method_Call_Template_3》= 《【ZHoldPlace_pythonMethodName】_【ZHoldPlace_Year_Int】_【ZHoldPlace_Month_Index】_xlsx_frame.to_excel(【ZHoldPlace_pythonMethodName】_【ZHoldPlace_Year_Int】_excel_writer,'【ZHoldPlace_Month_Index】',index=False)》
#  《Method_Call_Template_3》= 《【ZHoldPlace_pythonMethodName】_【ZHoldPlace_Year_Int】_excel_writer.save()》


#  《Method_Call_Template_4》= 《【ZHoldPlace_pythonMethodName】_【ZHoldPlace_Year_Int】_【ZHoldPlace_Month_Index】_xlsx_frame.to_excel(【ZHoldPlace_pythonMethodName】_【ZHoldPlace_Year_Int】_excel_writer,'【ZHoldPlace_Month_Index】',index=False)》
#  《Method_Call_Template_4》= 《【ZHoldPlace_pythonMethodName】_【ZHoldPlace_Year_Int】_excel_writer.save()》

#  《Tail_Define_Template_1》= 《J0_PROPS.put(tree_node_name+"record_date", "【ZHoldPlace_TomorrowDay_YYYYMMDD】")       ###  更新 记录日期》





############################## 头部定义  Begin ##############################
#《Head_Define_Template》
############################## 头部定义  End ##############################


############################## 循环method调用  Begin ##############################
#《Method_Call_Template》
############################## 循环method调用  End ##############################


############################## 尾部  Begin ##############################
#《Tail_Define_Template》
############################## 尾部  End ##############################


"""  方法调用模板
         ###################   头部定义 Begin ###################
stock_basic_book = load_workbook('股票列表.xlsx')
stock_basic_excel_writer = pd.ExcelWriter('股票列表.xlsx', engine='openpyxl')
stock_basic_excel_writer.book = stock_basic_book
stock_basic_excel_writer.sheets = dict((ws.title, ws) for ws in stock_basic_book.worksheets)
data_List_stock_basic = pd.DataFrame;
          ###################   头部定义 End ###################


          ###################   调用数据Method Begin ###################
data_L = pro.stock_basic(exchange='', list_status='L', fields='ts_code,symbol,name,area,industry,fullname,enname,market,exchange,curr_type,list_status,list_date,delist_date,is_hs')
data_List_stock_basic = data_List_stock_basic.append(data_L);

data_D = pro.stock_basic(exchange='', list_status='D', fields='ts_code,symbol,name,area,industry,fullname,enname,market,exchange,curr_type,list_status,list_date,delist_date,is_hs')
data_List_stock_basic = data_List_stock_basic.append(data_D);
          ###################   调用数据Method End ###################


          ###################   尾部定义 Begin ###################
data_List_stock_basic.to_excel(stock_basic_excel_writer,'股票列表')
stock_basic_excel_writer.save()
          ###################   尾部定义 End ###################
"""