import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.*;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class F5_CreateSqliteTableTool {

    static String zbinPath = System.getProperties().getProperty("user.home") + File.separator + "Desktop" + File.separator + "zbin" + File.separator;
    static String DB_NAME = "F5_ztest" + System.currentTimeMillis() + ".db";
    //    static String DB_NAME = "F5_ztest"+".db";
    static String SQLITE_DB_PATH = zbinPath + File.separator + DB_NAME;
    static String Tushare_Token = "43acb9a5ddc2cf73c6c4ea54796748f965457ed57daaa736bb778ea2";
    static int Tushare_Score = 120;  //  当前的积分

    static String Tushare_Site = "http://api.tushare.pro";
    static String requestTeplateStr = "{\"api_name\": \"ZTABLE_NAME\", \"token\": \"ZTOKEN\",\"params\": ZPARAMS,\"fields\": \"ZFields\"}";
    // {"api_name": "ZTABLE_NAME", "token": "ZTOKEN","params": {ZPARAMS},"fields": "ZFields"}
    static String SQLITE_CONNECT_URL = "jdbc:sqlite:" + zbinPath + DB_NAME;

    static String F5JsonPath = zbinPath + File.separator + "F5_stock_basic.json";

    static Set<String> TS_CODE_List = new HashSet<String>();
    static Map<String, String> TSCODE_NAME_MAP = new HashMap<String, String>();

    static NumberFormat nf = new DecimalFormat("0.00");



/*   已能调用接口
 【1】当前接口:stock_basic   股票基本信息接口:   【3685条数据】    数据会随新股的增加而增加  不是每日变化
{"api_name": "stock_basic", "token": "43acb9a5ddc2cf73c6c4ea54796748f965457ed57daaa736bb778ea2","params": {"is_hs":"","list_status":"L","exchange":""},"fields": "ts_code,symbol,name,area,industry,fullname,enname,market,exchange,curr_type,list_status,list_date,is_hs"}


【2】当前接口:suspend 需要积分:0 接口描述: 停复牌信息 【12条数据】 依据时间来得到每天停复牌的股票  每日变化
请求列表:{"api_name": "suspend", "token": "43acb9a5ddc2cf73c6c4ea54796748f965457ed57daaa736bb778ea2","params": {"ts_code":"","suspend_date":"20191018","resume_date":""},"fields": "ts_code,suspend_date,resume_date,ann_date,suspend_reason,reason_type"}
 table.tableData.size() = 12

【3】当前接口:hsgt_top10 需要积分:0 接口描述: 沪股通、深股通每日前十大成交详细数据  【20条数据】 每日变化
请求列表:{"api_name": "hsgt_top10", "token": "43acb9a5ddc2cf73c6c4ea54796748f965457ed57daaa736bb778ea2","params": {"ts_code":"","trade_date":"20191018","start_date":"","end_date":"","market_type":""},"fields": "trade_date,ts_code,name,close,change,rank,market_type,amount,net_amount,buy,sell"}

【4】当前接口:share_float 需要积分:120 接口描述: 限售股解禁数据  【每日都在变化】 随日变化 通过 时间 tscode查询
请求列表:{"api_name": "share_float", "token": "43acb9a5ddc2cf73c6c4ea54796748f965457ed57daaa736bb778ea2","params": {"ts_code":"","ann_date":"","float_date":"20191018","start_date":"","end_date":""},"fields": "ts_code,ann_date,float_date,float_share,float_ratio,holder_name,share_type"}

【5】当前接口: _index_basic 需要积分:0 接口描述: 获取指数基础信息。   【固定】
请求列表:{"api_name": "index_basic", "token": "43acb9a5ddc2cf73c6c4ea54796748f965457ed57daaa736bb778ea2","params": {"market":"SW","publisher":"","category":""},"fields": "ts_code,name,fullname,market,publisher,index_type,category,base_date,base_point,list_date,weight_rule,desc,exp_date"}

*/

/*  200 积分接口
【1】当前接口:index_daily 需要积分:200 接口描述: 获取指数每日行情，还可以通过bar接口获取。由于服务器压力，目前规则是单次调取最多取8000行记录，可以设置start和end日期补全。指数行情也可以通过通用行情接口获取数据．
请求列表:{"api_name": "index_daily", "token": "43acb9a5ddc2cf73c6c4ea54796748f965457ed57daaa736bb778ea2","params": {"ts_code":"","trade_date":"","start_date":"","end_date":""},"fields": "ts_code,trade_date,close,open,high,low,pre_close,change,pct_chg,vol,amount"}
=

 */

/*  300 积分接口
【1】当前接口:weekly 需要积分:300 接口描述: 获10取A股周线行情
请求列表:{"api_name": "weekly", "token": "43acb9a5ddc2cf73c6c4ea54796748f965457ed57daaa736bb778ea2","params": {"trade_date":"","start_date":"","end_date":""},"fields": "ts_code,trade_date,close,open,high,low,pre_close,change,vol,amount"}

【2】当前接口:monthly 需要积分:300 接口描述: 获取A股月线数据
请求列表:{"api_name": "monthly", "token": "43acb9a5ddc2cf73c6c4ea54796748f965457ed57daaa736bb778ea2","params": {"trade_date":"","start_date":"","end_date":""},"fields": "ts_code,trade_date,close,open,high,low,pre_close,change,pct_chg,vol,amount"}

【3】当前接口:daily_basic 需要积分:300 接口描述: 获取全部股票每日重要的基本面指标，可用于选股分析、报表展示等。
请求列表:{"api_name": "daily_basic", "token": "43acb9a5ddc2cf73c6c4ea54796748f965457ed57daaa736bb778ea2","params": {"ts_code":"","trade_date":"","start_date":"","end_date":""},"fields": "ts_code,trade_date,close,turnover_rate,turnover_rate_f,volume_ratio,pe,pe_ttm,pb,ps,ps_ttm,total_share,float_share,free_share,total_mv,circ_mv"}

【4】当前接口:dividend 需要积分:300 接口描述: 分红送股数据
请求列表:{"api_name": "dividend", "token": "43acb9a5ddc2cf73c6c4ea54796748f965457ed57daaa736bb778ea2","params": {"ann_date":"","record_date":"","ex_date":"","imp_ann_date":""},"fields": "ts_code,end_date,ann_date,div_proc,stk_div,stk_bo_rate,stk_co_rate,cash_div,cash_div_tax,record_date,ex_date,pay_date,div_listdate,imp_ann_date,base_date,base_share"}

【5】当前接口:top_list 需要积分:300 接口描述: 龙虎榜每日交易明细
请求列表:{"api_name": "top_list", "token": "43acb9a5ddc2cf73c6c4ea54796748f965457ed57daaa736bb778ea2","params": {"trade_date":"","ts_code":""},"fields": "trade_date,ts_code,name,close,pct_change,turnover_rate,amount,l_sell,l_buy,l_amount,net_amount,net_rate,amount_rate,float_values,reason"}

【6】当前接口:top_inst 需要积分:300 接口描述: 龙虎榜机构成交明细
请求列表:{"api_name": "top_inst", "token": "43acb9a5ddc2cf73c6c4ea54796748f965457ed57daaa736bb778ea2","params": {"trade_date":""},"fields": "trade_date,ts_code,exalter,buy,buy_rate,sell,sell_rate,net_buy"}


【7】当前接口:pledge_stat 需要积分:300 接口描述: 获取股权质押统计数据
请求列表:{"api_name": "pledge_stat", "token": "43acb9a5ddc2cf73c6c4ea54796748f965457ed57daaa736bb778ea2","params": {"ts_code":""},"fields": "ts_code,end_date,pledge_count,unrest_pledge,rest_pledge,total_share,pledge_ratio"}

【8】当前接口:pledge_detail 需要积分:300 接口描述: 获取股权质押明细数据
请求列表:{"api_name": "pledge_detail", "token": "43acb9a5ddc2cf73c6c4ea54796748f965457ed57daaa736bb778ea2","params": {"ts_code":""},"fields": "ts_code,ann_date,holder_name,pledge_amount,start_date,end_date,is_release,release_date,pledgor,holding_amount,pledged_amount,p_total_ratio,h_total_ratio,is_buyback"}

【9】当前接口:concept 需要积分:300 接口描述: 获取概念股分类，目前只有ts一个来源，未来将逐步增加来源
请求列表:{"api_name": "concept", "token": "43acb9a5ddc2cf73c6c4ea54796748f965457ed57daaa736bb778ea2","params": {"src":""},"fields": "code,name,src"}

【10】当前接口:concept_detail 需要积分:300 接口描述: 获取概念股分类明细数据
请求列表:{"api_name": "concept_detail", "token": "43acb9a5ddc2cf73c6c4ea54796748f965457ed57daaa736bb778ea2","params": {"id":"","ts_code":""},"fields": "id,concept_name,ts_code,name,in_date,out_date"}


【11】当前接口:block_trade 需要积分:300 接口描述: 大宗交易
请求列表:{"api_name": "block_trade", "token": "43acb9a5ddc2cf73c6c4ea54796748f965457ed57daaa736bb778ea2","params": {"trade_date":"","start_date":"","end_date":""},"fields": "ts_code,trade_date,price,vol,amount,buyer,seller"}




*/

/*  400 积分接口
【1】当前接口:index_weight 需要积分:400 接口描述: 获取各类指数成分和权重，月度数据 ，如需日度指数成分和权重，请联系 waditu@163.com
请求列表:{"api_name": "index_weight", "token": "43acb9a5ddc2cf73c6c4ea54796748f965457ed57daaa736bb778ea2","params": {"index_code":"","trade_date":"","start_date":"","end_date":""},"fields": "index_code,con_code,trade_date,weight"}

【2】当前接口:index_dailybasic 需要积分:400 接口描述: 目前只提供上证综指，深证成指，上证50，中证500，中小板指，创业板指的每日指标数据
请求列表:{"api_name": "index_dailybasic", "token": "43acb9a5ddc2cf73c6c4ea54796748f965457ed57daaa736bb778ea2","params": {"trade_date":"","start_date":"","end_date":""},"fields": "ts_code,trade_date,total_mv,float_mv,total_share,float_share,free_share,turnover_rate,turnover_rate_f,pe,pe_ttm,pb"}

 */


/*   500积分接口
【1】当前接口:income 需要积分:500 接口描述: 获取上市公司财务利润表数据
请求列表:{"api_name": "income", "token": "43acb9a5ddc2cf73c6c4ea54796748f965457ed57daaa736bb778ea2","params": {"ts_code":"","ann_date":"","start_date":"","end_date":"","period":"","report_type":"","comp_type":""},"fields": "ts_code,ann_date,f_ann_date,end_date,report_type,comp_type,basic_eps,diluted_eps,total_revenue,revenue,int_income,prem_earned,comm_income,n_commis_income,n_oth_income,n_oth_b_income,prem_income,out_prem,une_prem_reser,reins_income,n_sec_tb_income,n_sec_uw_income,n_asset_mg_income,oth_b_income,fv_value_chg_gain,invest_income,ass_invest_income,forex_gain,total_cogs,oper_cost,int_exp,comm_exp,biz_tax_surchg,sell_exp,admin_exp,fin_exp,assets_impair_loss,prem_refund,compens_payout,reser_insur_liab,div_payt,reins_exp,oper_exp,compens_payout_refu,insur_reser_refu,reins_cost_refund,other_bus_cost,operate_profit,non_oper_income,non_oper_exp,nca_disploss,total_profit,income_tax,n_income,n_income_attr_p,minority_gain,oth_compr_income,t_compr_income,compr_inc_attr_p,compr_inc_attr_m_s,ebit,ebitda,insurance_exp,undist_profit,distable_profit,update_flag"}

【2】当前接口:balancesheet 需要积分:500 接口描述: 获取上市公司资产负债表
请求列表:{"api_name": "balancesheet", "token": "43acb9a5ddc2cf73c6c4ea54796748f965457ed57daaa736bb778ea2","params": {"ts_code":"","ann_date":"","start_date":"","end_date":"","period":"","report_type":"","comp_type":""},"fields": "ts_code,ann_date,f_ann_date,end_date,report_type,comp_type,total_share,cap_rese,undistr_porfit,surplus_rese,special_rese,money_cap,trad_asset,notes_receiv,accounts_receiv,oth_receiv,prepayment,div_receiv,int_receiv,inventories,amor_exp,nca_within_1y,sett_rsrv,loanto_oth_bank_fi,premium_receiv,reinsur_receiv,reinsur_res_receiv,pur_resale_fa,oth_cur_assets,total_cur_assets,fa_avail_for_sale,htm_invest,lt_eqt_invest,invest_real_estate,time_deposits,oth_assets,lt_rec,fix_assets,cip,const_materials,fixed_assets_disp,produc_bio_assets,oil_and_gas_assets,intan_assets,r_and_d,goodwill,lt_amor_exp,defer_tax_assets,decr_in_disbur,oth_nca,total_nca,cash_reser_cb,depos_in_oth_bfi,prec_metals,deriv_assets,rr_reins_une_prem,rr_reins_outstd_cla,rr_reins_lins_liab,rr_reins_lthins_liab,refund_depos,ph_pledge_loans,refund_cap_depos,indep_acct_assets,client_depos,client_prov,transac_seat_fee,invest_as_receiv,total_assets,lt_borr,st_borr,cb_borr,depos_ib_deposits,loan_oth_bank,trading_fl,notes_payable,acct_payable,adv_receipts,sold_for_repur_fa,comm_payable,payroll_payable,taxes_payable,int_payable,div_payable,oth_payable,acc_exp,deferred_inc,st_bonds_payable,payable_to_reinsurer,rsrv_insur_cont,acting_trading_sec,acting_uw_sec,non_cur_liab_due_1y,oth_cur_liab,total_cur_liab,bond_payable,lt_payable,specific_payables,estimated_liab,defer_tax_liab,defer_inc_non_cur_liab,oth_ncl,total_ncl,depos_oth_bfi,deriv_liab,depos,agency_bus_liab,oth_liab,prem_receiv_adva,depos_received,ph_invest,reser_une_prem,reser_outstd_claims,reser_lins_liab,reser_lthins_liab,indept_acc_liab,pledge_borr,indem_payable,policy_div_payable,total_liab,treasury_share,ordin_risk_reser,forex_differ,invest_loss_unconf,minority_int,total_hldr_eqy_exc_min_int,total_hldr_eqy_inc_min_int,total_liab_hldr_eqy,lt_payroll_payable,oth_comp_income,oth_eqt_tools,oth_eqt_tools_p_shr,lending_funds,acc_receivable,st_fin_payable,payables,hfs_assets,hfs_sales,update_flag"}

【3】当前接口:cashflow 需要积分:500 接口描述: 获取上市公司现金流量表
请求列表:{"api_name": "cashflow", "token": "43acb9a5ddc2cf73c6c4ea54796748f965457ed57daaa736bb778ea2","params": {"ts_code":"","ann_date":"","start_date":"","end_date":"","period":"","report_type":"","comp_type":""},"fields": "ts_code,ann_date,f_ann_date,end_date,comp_type,report_type,net_profit,finan_exp,c_fr_sale_sg,recp_tax_rends,n_depos_incr_fi,n_incr_loans_cb,n_inc_borr_oth_fi,prem_fr_orig_contr,n_incr_insured_dep,n_reinsur_prem,n_incr_disp_tfa,ifc_cash_incr,n_incr_disp_faas,n_incr_loans_oth_bank,n_cap_incr_repur,c_fr_oth_operate_a,c_inf_fr_operate_a,c_paid_goods_s,c_paid_to_for_empl,c_paid_for_taxes,n_incr_clt_loan_adv,n_incr_dep_cbob,c_pay_claims_orig_inco,pay_handling_chrg,pay_comm_insur_plcy,oth_cash_pay_oper_act,st_cash_out_act,n_cashflow_act,oth_recp_ral_inv_act,c_disp_withdrwl_invest,c_recp_return_invest,n_recp_disp_fiolta,n_recp_disp_sobu,stot_inflows_inv_act,c_pay_acq_const_fiolta,c_paid_invest,n_disp_subs_oth_biz,oth_pay_ral_inv_act,n_incr_pledge_loan,stot_out_inv_act,n_cashflow_inv_act,c_recp_borrow,proc_issue_bonds,oth_cash_recp_ral_fnc_act,stot_cash_in_fnc_act,free_cashflow,c_prepay_amt_borr,c_pay_dist_dpcp_int_exp,incl_dvd_profit_paid_sc_ms,oth_cashpay_ral_fnc_act,stot_cashout_fnc_act,n_cash_flows_fnc_act,eff_fx_flu_cash,n_incr_cash_cash_equ,c_cash_equ_beg_period,c_cash_equ_end_period,c_recp_cap_contrib,incl_cash_rec_saims,uncon_invest_loss,prov_depr_assets,depr_fa_coga_dpba,amort_intang_assets,lt_amort_deferred_exp,decr_deferred_exp,incr_acc_exp,loss_disp_fiolta,loss_scr_fa,loss_fv_chg,invest_loss,decr_def_inc_tax_assets,incr_def_inc_tax_liab,decr_inventories,decr_oper_payable,incr_oper_payable,others,im_net_cashflow_oper_act,conv_debt_into_cap,conv_copbonds_due_within_1y,fa_fnc_leases,end_bal_cash,beg_bal_cash,end_bal_cash_equ,beg_bal_cash_equ,im_n_incr_cash_equ,update_flag"}

【4】当前接口:express 需要积分:500 接口描述: 获取上市公司业绩快报
请求列表:{"api_name": "express", "token": "43acb9a5ddc2cf73c6c4ea54796748f965457ed57daaa736bb778ea2","params": {"ts_code":"","ann_date":"","start_date":"","end_date":"","period":""},"fields": "ts_code,ann_date,end_date,revenue,operate_profit,total_profit,n_income,total_assets,total_hldr_eqy_exc_min_int,diluted_eps,diluted_roe,yoy_net_profit,bps,yoy_sales,yoy_op,yoy_tp,yoy_dedu_np,yoy_eps,yoy_roe,growth_assets,yoy_equity,growth_bps,or_last_year,op_last_year,tp_last_year,np_last_year,eps_last_year,open_net_assets,open_bps,perf_summary,is_audit,remark"}

【5】当前接口:fina_indicator 需要积分:500 接口描述: 获取上市公司财务指标数据，为避免服务器压力，现阶段每次请求最多返回60条记录，可通过设置日期多次请求获取更多数据。
请求列表:{"api_name": "fina_indicator", "token": "43acb9a5ddc2cf73c6c4ea54796748f965457ed57daaa736bb778ea2","params": {"ann_date":"","start_date":"","end_date":"","period":""},"fields": "ts_code,ann_date,end_date,eps,dt_eps,total_revenue_ps,revenue_ps,capital_rese_ps,surplus_rese_ps,undist_profit_ps,extra_item,profit_dedt,gross_margin,current_ratio,quick_ratio,cash_ratio,invturn_days,arturn_days,inv_turn,ar_turn,ca_turn,fa_turn,assets_turn,op_income,valuechange_income,interst_income,daa,ebit,ebitda,fcff,fcfe,current_exint,noncurrent_exint,interestdebt,netdebt,tangible_asset,working_capital,networking_capital,invest_capital,retained_earnings,diluted2_eps,bps,ocfps,retainedps,cfps,ebit_ps,fcff_ps,fcfe_ps,netprofit_margin,grossprofit_margin,cogs_of_sales,expense_of_sales,profit_to_gr,saleexp_to_gr,adminexp_of_gr,finaexp_of_gr,impai_ttm,gc_of_gr,op_of_gr,ebit_of_gr,roe,roe_waa,roe_dt,roa,npta,roic,roe_yearly,roa2_yearly,roe_avg,opincome_of_ebt,investincome_of_ebt,n_op_profit_of_ebt,tax_to_ebt,dtprofit_to_profit,salescash_to_or,ocf_to_or,ocf_to_opincome,capitalized_to_da,debt_to_assets,assets_to_eqt,dp_assets_to_eqt,ca_to_assets,nca_to_assets,tbassets_to_totalassets,int_to_talcap,eqt_to_talcapital,currentdebt_to_debt,longdeb_to_debt,ocf_to_shortdebt,debt_to_eqt,eqt_to_debt,eqt_to_interestdebt,tangibleasset_to_debt,tangasset_to_intdebt,tangibleasset_to_netdebt,ocf_to_debt,ocf_to_interestdebt,ocf_to_netdebt,ebit_to_interest,longdebt_to_workingcapital,ebitda_to_debt,turn_days,roa_yearly,roa_dp,fixed_assets,profit_prefin_exp,non_op_profit,op_to_ebt,nop_to_ebt,ocf_to_profit,cash_to_liqdebt,cash_to_liqdebt_withinterest,op_to_liqdebt,op_to_debt,roic_yearly,total_fa_trun,profit_to_op,q_opincome,q_investincome,q_dtprofit,q_eps,q_netprofit_margin,q_gsprofit_margin,q_exp_to_sales,q_profit_to_gr,q_saleexp_to_gr,q_adminexp_to_gr,q_finaexp_to_gr,q_impair_to_gr_ttm,q_gc_to_gr,q_op_to_gr,q_roe,q_dt_roe,q_npta,q_opincome_to_ebt,q_investincome_to_ebt,q_dtprofit_to_profit,q_salescash_to_or,q_ocf_to_sales,q_ocf_to_or,basic_eps_yoy,dt_eps_yoy,cfps_yoy,op_yoy,ebt_yoy,netprofit_yoy,dt_netprofit_yoy,ocf_yoy,roe_yoy,bps_yoy,assets_yoy,eqt_yoy,tr_yoy,or_yoy,q_gr_yoy,q_gr_qoq,q_sales_yoy,q_sales_qoq,q_op_yoy,q_op_qoq,q_profit_yoy,q_profit_qoq,q_netprofit_yoy,q_netprofit_qoq,equity_yoy,rd_exp,update_flag"}

【6】当前接口:fina_audit 需要积分:500 接口描述: 获取上市公司定期财务审计意见数据
请求列表:{"api_name": "fina_audit", "token": "43acb9a5ddc2cf73c6c4ea54796748f965457ed57daaa736bb778ea2","params": {"ts_code":"","ann_date":"","start_date":"","end_date":"","period":""},"fields": "ts_code,ann_date,end_date,audit_result,audit_fees,audit_agency,audit_sign"}

【7】当前接口:fina_mainbz 需要积分:500 接口描述: 获得上市公司主营业务构成，分地区和产品两种方式
请求列表:{"api_name": "fina_mainbz", "token": "43acb9a5ddc2cf73c6c4ea54796748f965457ed57daaa736bb778ea2","params": {"period":"","period":"","type":"","start_date":"","end_date":""},"fields": "ts_code,end_date,bz_item,bz_sales,bz_profit,bz_cost,curr_type,update_flag"}


【8】当前接口:disclosure_date 需要积分:500 接口描述: 获取财报披露计划日期
请求列表:{"api_name": "disclosure_date", "token": "43acb9a5ddc2cf73c6c4ea54796748f965457ed57daaa736bb778ea2","params": {"end_date":"","pre_date":"","actual_date":""},"fields": "ts_code,ann_date,end_date,pre_date,actual_date,modify_date"}


 */



/*   600积分接口
【1】当前接口:stk_limit 需要积分:600 接口描述: 获取全市场（包含A/B股和基金）每日涨跌停价格，包括涨停价格，跌停价格等，每个交易日8点40左右更新当日股票涨跌停价格。
请求列表:{"api_name": "stk_limit", "token": "43acb9a5ddc2cf73c6c4ea54796748f965457ed57daaa736bb778ea2","params": {"ts_code":"","trade_date":"","start_date":"","end_date":""},"fields": "trade_date,ts_code,pre_close,up_limit,down_limit"}

【2】当前接口:forecast 需要积分:600 接口描述: 获取业绩预告数据
请求列表:{"api_name": "forecast", "token": "43acb9a5ddc2cf73c6c4ea54796748f965457ed57daaa736bb778ea2","params": {"ts_code":"","ann_date":"","start_date":"","end_date":"","period":"","type":""},"fields": "ts_code,ann_date,end_date,type,p_change_min,p_change_max,net_profit_min,net_profit_max,last_parent_net,first_ann_date,summary,change_reason"}

【3】当前接口:repurchase 需要积分:600 接口描述: 获取上市公司回购股票数据
请求列表:{"api_name": "repurchase", "token": "43acb9a5ddc2cf73c6c4ea54796748f965457ed57daaa736bb778ea2","params": {"ann_date":"","start_date":"","end_date":""},"fields": "ts_code,ann_date,end_date,proc,exp_date,vol,amount,high_limit,low_limit"}

【4】当前接口:stk_account 需要积分:600 接口描述: 获取股票账户开户数据，统计周期为一周
请求列表:{"api_name": "stk_account", "token": "43acb9a5ddc2cf73c6c4ea54796748f965457ed57daaa736bb778ea2","params": {"date":"","start_date":"","end_date":""},"fields": "date,weekly_new,total,weekly_hold,weekly_trade"}


【5】当前接口:stk_holdernumber 需要积分:600 接口描述: 获取上市公司股东户数数据，数据不定期公布
请求列表:{"api_name": "stk_holdernumber", "token": "43acb9a5ddc2cf73c6c4ea54796748f965457ed57daaa736bb778ea2","params": {"enddate":"","start_date":"","end_date":""},"fields": "ts_code,ann_date,end_date,holder_num"}

【6】当前接口:index_weekly 需要积分:600 接口描述: 获取指数周线行情
请求列表:{"api_name": "index_weekly", "token": "43acb9a5ddc2cf73c6c4ea54796748f965457ed57daaa736bb778ea2","params": {"trade_date":"","start_date":"","end_date":""},"fields": "ts_code,trade_date,close,open,high,low,pre_close,change,pct_chg,vol,amount"}

【7】当前接口:index_monthly 需要积分:600 接口描述: 获取指数月线行情,每月更新一次
请求列表:{"api_name": "index_monthly", "token": "43acb9a5ddc2cf73c6c4ea54796748f965457ed57daaa736bb778ea2","params": {"trade_date":"","start_date":"","end_date":""},"fields": "ts_code,trade_date,close,open,high,low,pre_close,change,pct_chg,vol,amount"}


 */

/*   1500积分接口

【1】当前接口:moneyflow 需要积分:1500 接口描述: 获取沪深A股票资金流向数据，分析大单小单成交情况，用于判别资金动向
请求列表:{"api_name": "moneyflow", "token": "43acb9a5ddc2cf73c6c4ea54796748f965457ed57daaa736bb778ea2","params": {"ts_code":"","trade_date":"","start_date":"","end_date":""},"fields": "ts_code,trade_date,buy_sm_vol,buy_sm_amount,sell_sm_vol,sell_sm_amount,buy_md_vol,buy_md_amount,sell_md_vol,sell_md_amount,buy_lg_vol,buy_lg_amount,sell_lg_vol,sell_lg_amount,buy_elg_vol,buy_elg_amount,sell_elg_vol,sell_elg_amount,net_mf_vol,net_mf_amount"}

 */



/*   2000积分接口
 【1】当前接口: stk_rewards 需要积分:2000 接口描述: 获取上市公司管理层薪酬和持股
请求列表:{"api_name": "stk_rewards", "token": "43acb9a5ddc2cf73c6c4ea54796748f965457ed57daaa736bb778ea2","params": {"ts_code":"","end_date":""},"fields": "ts_code,ann_date,end_date,name,title,reward,hold_vol"}

【2】当前接口:limit_list 需要积分:2000 接口描述: 获取每日涨跌停股票统计，包括封闭时间和打开次数等数据，帮助用户快速定位近期强（弱）势股，以及研究超短线策略。
请求列表:{"api_name": "limit_list", "token": "43acb9a5ddc2cf73c6c4ea54796748f965457ed57daaa736bb778ea2","params": {"trade_date":"","ts_code":"","limit_type":"","start_date":"","end_date":""},"fields": "trade_date,ts_code,name,close,pct_chg,amp,fc_ratio,fl_ratio,fd_amount,first_time,last_time,open_times,strth,limit"}

【3】当前接口:ggt_daily 需要积分:2000 接口描述: 获取港股通每日成交信息，数据从2014年开始
请求列表:{"api_name": "ggt_daily", "token": "43acb9a5ddc2cf73c6c4ea54796748f965457ed57daaa736bb778ea2","params": {"trade_date":"","start_date":"","end_date":""},"fields": "trade_date,buy_amount,buy_volume,sell_amount,sell_volume"}

【4】当前接口:margin_detail 需要积分:2000 接口描述: 获取沪深两市每日融资融券明细
请求列表:{"api_name": "margin_detail", "token": "43acb9a5ddc2cf73c6c4ea54796748f965457ed57daaa736bb778ea2","params": {"trade_date":"","start_date":"","end_date":""},"fields": "trade_date,ts_code,name,rzye,rqye,rzmre,rqyl,rzche,rqchl,rqmcl,rzrqye"}

【5】当前接口:stk_holdertrade 需要积分:2000 接口描述: 获取上市公司增减持数据，了解重要股东近期及历史上的股份增减变化
请求列表:{"api_name": "stk_holdertrade", "token": "43acb9a5ddc2cf73c6c4ea54796748f965457ed57daaa736bb778ea2","params": {"ann_date":"","start_date":"","end_date":"","trade_type":"","holder_type":""},"fields": "ts_code,ann_date,holder_name,holder_type,in_de,change_vol,change_ratio,after_share,after_ratio,avg_price,total_share,begin_date,close_date"}

【6】当前接口:index_classify 需要积分:2000 接口描述: 获取申万行业分类，包括申万28个一级分类，104个二级分类，227个三级分类的列表信息
请求列表:{"api_name": "index_classify", "token": "43acb9a5ddc2cf73c6c4ea54796748f965457ed57daaa736bb778ea2","params": {"index_code":"","level":"","src":""},"fields": "index_code,industry_name,level,industry_code,src"}


【7】当前接口:index_member 需要积分:2000 接口描述: 申万行业成分
请求列表:{"api_name": "index_member", "token": "43acb9a5ddc2cf73c6c4ea54796748f965457ed57daaa736bb778ea2","params": {"index_code":"","ts_code":"","is_new":""},"fields": "index_code,index_name,con_code,con_name,in_date,out_date,is_new"}




*/
/*
5000积分接口
【1】当前接口:ggt_monthly 需要积分:5000 接口描述: 港股通每月成交信息，数据从2014年开始
请求列表:{"api_name": "ggt_monthly", "token": "43acb9a5ddc2cf73c6c4ea54796748f965457ed57daaa736bb778ea2","params": {"month":"","start_month":"","end_month":""},"fields": "month,day_buy_amt,day_buy_vol,day_sell_amt,day_sell_vol,total_buy_amt,total_buy_vol,total_sell_amt,total_sell_vol"}
*/


    public static void main(String[] args) {
        if (!CheckFilePath(SQLITE_DB_PATH)) {
            InitTable(SQLITE_DB_PATH);
        }
        InitTableData(SQLITE_DB_PATH);


        System.out.println("TS_CODE_List.size = " + TS_CODE_List);


        Object[] ts_code = TS_CODE_List.toArray();

        for (int i = 0; i < ts_code.length; i++) {
            System.out.println("索引" + i + " : " + ts_code[i].toString());
        }


        PrintKeyAndValue(TSCODE_NAME_MAP);
    }


    public static void PrintKeyAndValue(Map<String, String> map) {
        Map.Entry<String, String> entry;

        if (map != null) {
            int size = map.size();
            int index = 1;
            Iterator iterator = map.entrySet().iterator();


            while (iterator.hasNext()) {
                entry = (Map.Entry<String, String>) iterator.next();
                String key = entry.getKey();  //Map的Value
                String value = entry.getValue();  //Map的Value
                System.out.println("Map.Size = " + size + " 当前索引:" + index + "   Key:" + key + "  Value:" + value);
                index++;
            }
        }
    }

    public static String HttpPostWithBody2(String bodyJson) {

        BufferedReader reader = null;
        try {
            URL url = new URL(Tushare_Site);// 创建连接
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestMethod("POST"); // 设置请求方式
            // connection.setRequestProperty("Accept", "application/json"); // 设置接收数据的格式
            connection.setRequestProperty("Content-Type", "application/json"); // 设置发送数据的格式
            connection.connect();
            //一定要用BufferedReader 来接收响应， 使用字节来接收响应的方法是接收不到内容的
            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8"); // utf-8编码
            out.append(bodyJson);
            out.flush();
            out.close();
            // 读取响应
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            String line;
            String res = "";
            while ((line = reader.readLine()) != null) {
                res += line;
            }
            reader.close();

            //如果一定要使用如下方式接收响应数据， 则响应必须为: response.getWriter().print(StringUtils.join("{\"errCode\":\"1\",\"errMsg\":\"", message, "\"}")); 来返回
//            int length = (int) connection.getContentLength();// 获取长度
//            if (length != -1) {
//                byte[] data = new byte[length];
//                byte[] temp = new byte[512];
//                int readLen = 0;
//                int destPos = 0;
//                while ((readLen = is.read(temp)) > 0) {
//                    System.arraycopy(temp, 0, data, destPos, readLen);
//                    destPos += readLen;
//                }
//                String result = new String(data, "UTF-8"); // utf-8编码
//                System.out.println(result);
//                return result;
//            }

            return res;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "error"; // 自定义错误信息
    }


    public static String HttpPostWithBody(String RequestBodyJson) {
        String JsonResult = "";
        org.jsoup.Connection connection = Jsoup.connect(Tushare_Site);
        connection.header("Content-Type", "application/json; charset=UTF-8"); //这是重点
        connection.data("aaa", "ccc"); //这是重点

        connection.header("Accept", "text/plain, */*; q=0.01");
        connection.header("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
        connection.postDataCharset("utf-8");
        connection.ignoreContentType(true);
        connection.timeout(15000);
        connection.requestBody(RequestBodyJson);
        try {
            Document document = connection.post();
            //    con.postDataCharset("GBK");

            //  System.out.println("===========BODY身体===========");
            //   System.out.println(ascii2Native(document.body().text()));
            JsonResult = ascii2Native(document.body().text());
            if (!JSONUtil.isJson(JsonResult)) {
                JsonResult = "";
                System.out.println("解析的字符串经过ascii2Native处理后不是Json BODY长度:" + document.body().text().length());

                System.out.println("解析的字符串经过ascii2Native处理后不是Json BODY:" + document.body().text());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return JsonResult;
    }


    public static boolean CheckFilePath(String path) {
        boolean falg = false;
        File curFile = new File(path);
        falg = curFile.exists();
        return falg;
    }

    public static void InitTableData(String DBPath) {


        for (int i = 0; i < SQLite_Tables.size(); i++) {
            DB_Table table = SQLite_Tables.get(i);
            System.out.println("======================================");

            // 9 daily  日线行情   需要使用 TS_CODE_LIST
            // 12  adj_factor  复权因子   需要使用 TS_CODE_LIST
            // 36   top10_holders    前10总股股东
            // 37   top10_floatholders  前10流通股东
            if (table.tableIndex == 9 || table.tableIndex == 12 || table.tableIndex == 36
                    || table.tableIndex == 37) {
                System.out.println("当前接口:" + table.tableName + " 需要积分:" + table.scoreLimit + " 接口描述: " + table.tableDesc);
                System.out.println("请求列表:" + table.createRequestBody());

                Object[] ts_code = TS_CODE_List.toArray();
                for (int j = 0; j < ts_code.length; j++) {
                    String tsCode = ts_code[j].toString();
                    System.out.println(table.tableDesc + "下载中!   索引:" + j + "  总长:" + ts_code.length + "  当前股票:" + tsCode);
                    long netTime1 = System.currentTimeMillis();
                    table.initJsonData(HttpPostWithBody(table.createRequestBodyWithTS_Code(tsCode)), table.tableName);
                    try {
                        Thread.sleep(800);   //  每次执行 休息 500 毫秒
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (table.tableData.size() > 1000) {
                        System.out.println("长度大于30000行执行插入  索引:" + j + "  总长:" + ts_code.length + "  当前股票:" + tsCode);

                        long netTime2 = System.currentTimeMillis();
                        long netTimeDistance = netTime2 - netTime1;

                        System.out.println("数据量:" + table.tableData.size() + "   程序网络花销时间: " + Double.parseDouble(nf.format((Double) (netTimeDistance / (1024d)))) + "秒!");

                        long dbTime1 = System.currentTimeMillis();


                        table.insertTableData2DB();
//                        for (int k = 0; k < table.tableData.size(); k++) {
//                            table.tableData.get(k).fieldAndValueMap.clear();
//                        }
                        long dbTime2 = System.currentTimeMillis();
                        long dbTimeDistance = dbTime2 - dbTime1;
                        System.out.println("数据量:" + table.tableData.size() + "   程序数据库花销时间: " + Double.parseDouble(nf.format((Double) (dbTimeDistance / (1024d)))) + "秒!");

                        table.tableData.clear();

                    }
                }

                if (table.tableData.size() > 0) {
                    table.insertTableData2DB();
//                        for (int k = 0; k < table.tableData.size(); k++) {
//                            table.tableData.get(k).fieldAndValueMap.clear();
//                        }
                    table.tableData.clear();

                }


            } else {

                if (table.scoreLimit <= Tushare_Score) {
                    // System.out.println("BODY0000:"+ HttpPostWithBody2(table.createRequestBody())); ;
                    // System.out.println("BODY1:"+ ascii2Native(HttpPostWithBody2(table.createRequestBody()))); ;
                    table.initJsonData(HttpPostWithBody(table.createRequestBody()), table.tableName);
                    System.out.println("当前接口:" + table.tableName + " 需要积分:" + table.scoreLimit + " 接口描述: " + table.tableDesc);
                    System.out.println("请求列表:" + table.createRequestBody());
                    System.out.println(" table.tableData.size() = " + table.tableData.size());

                    if (table.tableData.size() > 0) {
                        table.insertTableData2DB();
                    }

                } else {
                    System.out.println("当前接口:" + table.tableName + " 需要积分:" + table.scoreLimit + " 当前积分不够 无法调用!");
                    System.out.println("当前接口:" + table.tableName + " 需要积分:" + table.scoreLimit + " 接口描述: " + table.tableDesc);
                    System.out.println("请求列表:" + table.createRequestBody());
                }

            }
            System.out.println("======================================");


        }

    }


    public static String ascii2Native(String str) {

        StringBuilder sb = new StringBuilder();

        int begin = 0;

        int index = str.indexOf(PREFIX);

        while (index != -1) {
            sb.append(str.substring(begin, index));
            sb.append(ascii2Char(str.substring(index, index + 6)));
            begin = index + 6;
            index = str.indexOf(PREFIX, begin);
        }


        sb.append(str.substring(begin));

        return sb.toString();

    }

    static String PREFIX = "\\u";

    static String buildRepeatStr(int repeatNum, String str) {
        String mStr = "";

        for (int i = 0; i < repeatNum; i++) {
            mStr = mStr + str;
        }
        return mStr;
    }

    static char ascii2Char(String str) {

        if (str.length() != 6) {

            throw new IllegalArgumentException("Ascii string of a native character must be 6 character.");

        }

        if (!PREFIX.equals(str.substring(0, 2))) {

            throw new IllegalArgumentException("Ascii string of a native character must start with \"\\u\".");

        }

        String tmp = str.substring(2, 4);

        int code = Integer.parseInt(tmp, 16) << 8;

        tmp = str.substring(4, 6);

        code += Integer.parseInt(tmp, 16);

        return (char) code;

    }


    public static void InitTable(String DBPath) {

        Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");  // 1. 首先把 JDBC 通过 Class.forName 加载到 JVM
            // 创建数据库的操作 如果没有该文件 那么创建该文件  有该文件就得到该db文件的链接
            SQLITE_CONNECT_URL = "jdbc:sqlite:" + DBPath;
            c = DriverManager.getConnection(SQLITE_CONNECT_URL);

            System.out.println("创建数据库:" + DBPath + "成功！");

            if (c == null) {
                System.out.println("connect is null !");
                return;
            }

/*            Properties prop = c.getClientInfo();
            if (prop != null) {
                Object[] pronameSet = prop.stringPropertyNames().toArray();
                if (pronameSet != null) {
                    for (int i = 0; i < pronameSet.length; i++) {
                        System.out.println("name:" + pronameSet[i] + "   value:" + prop.getProperty(pronameSet[i] + ""));
                    }


                }

            } else {
                System.out.println("prop is null ! ");
            }
            c.getCatalog();
            System.out.println("cataLog=" + c.getCatalog());*/


            Statement stmt = c.createStatement();

            ArrayList<String> tableSqlList = getCreateTableSql();


//            当executeUpdate(sql)是INSERT、UPDATE 或 DELETE 语句时，返回的是受影响的行数（即更新的行数）。
//            当executeUpdate(sql)是CREATE TABLE 或 DROP TABLE 等不操作行的语句，executeUpdate 的返回值是零。

            for (int i = 0; i < tableSqlList.size(); i++) {
                String tableSql = tableSqlList.get(i);
                int returnValue = stmt.executeUpdate(tableSql);
                if (returnValue == 0) {
                    System.out.println("============================");
                    System.out.println("创表语句: " + tableSql);
                    System.out.println("执行成功!");
                    System.out.println("============================");
                } else {
                    System.out.println("============================");
                    System.out.println("创表语句: " + tableSql);
                    System.out.println("执行失败!");
                    System.out.println("============================");
                }
            }

/*            //  stmt.executeUpdate(sql); // 执行数据库结构的变更
            ResultSet resultSet = stmt.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("_id");
                String logpath = resultSet.getString("logpath");
                String type = resultSet.getString("type");
                String state = resultSet.getString("state");
                System.out.println("ID = " + id);
                System.out.println("logpath = " + logpath);
                System.out.println("type = " + type);
                System.out.println("state = " + state);

                System.out.println();
            }
            resultSet.close();*/

            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            // System.exit(0);
        }
    }

    public static ArrayList<String> getCreateTableSql() {
        ArrayList<String> createTableStrList = new ArrayList<String>();
        for (int i = 0; i < SQLite_Tables.size(); i++) {
            DB_Table table = SQLite_Tables.get(i);
//            System.out.println("==========================");
//            System.out.println(table.createTableSqlStr());
            if (table.viceTableIndex == 1) {
                createTableStrList.add(table.createTableSqlStr());
            }

        }

        return createTableStrList;
    }


    static ArrayList<DB_Table> SQLite_Tables = new ArrayList<DB_Table>();

    static {
// 【table_1_stock_basic】stock_basic    https://tushare.pro/document/2?doc_id=25
// POST:  Site: https://getman.cn/AJ38B
// TestSite:  http://api.tushare.pro            http://api.waditu.com
// BODY:
// 查询所有目前还上市的股票  L上市 D退市 P暂停上市
        //  在市股票 L
// {"api_name": "stock_basic", "token": "43acb9a5ddc2cf73c6c4ea54796748f965457ed57daaa736bb778ea2", "params": {}, "fields": "ts_code,symbol,name,area,industry,fullname,enname,market,exchange,curr_type,list_status,list_date,is_hs"}

//  退市股票 D  无效
// {"api_name": "stock_basic", "token": "43acb9a5ddc2cf73c6c4ea54796748f965457ed57daaa736bb778ea2", "params": {"list_stauts":"D"}, "fields": "ts_code,symbol,name,area,industry,fullname,enname,market,exchange,curr_type,list_status,list_date,delist_date,is_hs"}

// 暂停上市股票 P  无效
        // {"api_name": "stock_basic", "token": "43acb9a5ddc2cf73c6c4ea54796748f965457ed57daaa736bb778ea2", "params": {"list_stauts":"P"}, "fields": "ts_code,symbol,name,area,industry,fullname,enname,market,exchange,curr_type,list_status,list_date,delist_date,is_hs"}


        DB_Table StockBasic = new DB_Table("stock_basic");
        Table_Item StockBasic_ts_code = new Table_Item("ts_code", SQLITE_TYPE.TEXT, "数字代码");
        StockBasic_ts_code.isPrimaryKey = true;
        Table_Item StockBasic_symbol = new Table_Item("symbol", SQLITE_TYPE.TEXT, "股票代码");
        Table_Item StockBasic_name = new Table_Item("name", SQLITE_TYPE.TEXT, "股票名称");
        Table_Item StockBasic_area = new Table_Item("area", SQLITE_TYPE.TEXT, "所在地域");
        Table_Item StockBasic_industry = new Table_Item("industry", SQLITE_TYPE.TEXT, "所属行业");
        Table_Item StockBasic_fullname = new Table_Item("fullname", SQLITE_TYPE.TEXT, "股票全称");
        Table_Item StockBasic_enname = new Table_Item("enname", SQLITE_TYPE.TEXT, "英文全称");
        Table_Item StockBasic_market = new Table_Item("market", SQLITE_TYPE.TEXT, "市场类型");  // （主板/中小板/创业板/科创板）
        Table_Item StockBasic_exchange = new Table_Item("exchange", SQLITE_TYPE.TEXT, "交易所代码");
        Table_Item StockBasic_curr_type = new Table_Item("curr_type", SQLITE_TYPE.TEXT, "交易货币");
        Table_Item StockBasic_list_status = new Table_Item("list_status", SQLITE_TYPE.TEXT, "上市状态"); // L上市 D退市 P暂停上市
        Table_Item StockBasic_list_date = new Table_Item("list_date", SQLITE_TYPE.TEXT, "上市日期");
        Table_Item StockBasic_delist_date = new Table_Item("delist_date", SQLITE_TYPE.TEXT, "退市日期");
        Table_Item StockBasic_is_hs = new Table_Item("is_hs", SQLITE_TYPE.TEXT, "沪深港通标的"); // N否 H沪股通 S深股通
        StockBasic.addTableItem(StockBasic_ts_code);
        StockBasic.addTableItem(StockBasic_symbol);
        StockBasic.addTableItem(StockBasic_name);
        StockBasic.addTableItem(StockBasic_area);
        StockBasic.addTableItem(StockBasic_industry);
        StockBasic.addTableItem(StockBasic_fullname);
        StockBasic.addTableItem(StockBasic_enname);
        StockBasic.addTableItem(StockBasic_market);
        StockBasic.addTableItem(StockBasic_exchange);
        StockBasic.addTableItem(StockBasic_curr_type);
        StockBasic.addTableItem(StockBasic_list_status);
        StockBasic.addTableItem(StockBasic_list_date);
        // StockBasic.addTableItem(StockBasic_delist_date);
        StockBasic.addTableItem(StockBasic_is_hs);

        Table_Input_Param StockBasic_Input_is_hs = new Table_Input_Param("is_hs", "str", "");
        StockBasic_Input_is_hs.desc = "[是否沪深港通标的] N否 H沪股通 S深股通";
        Table_Input_Param StockBasic_Input_list_status = new Table_Input_Param("list_status", "str", " L");
        StockBasic_Input_list_status.desc = "[是否沪深港通标的]  N否  H沪股通  S深股通";
        Table_Input_Param StockBasic_Input_exchange = new Table_Input_Param("exchange", "str", "");
        StockBasic_Input_exchange.desc = "[交易所]  SSE上交所  SZSE深交所 ";
        StockBasic.addTableInputParam(StockBasic_Input_is_hs);
        StockBasic.addTableInputParam(StockBasic_Input_list_status);
        StockBasic.addTableInputParam(StockBasic_Input_exchange);


        StockBasic.tableIndex = 1;
        StockBasic.viceTableIndex = 1;
        StockBasic.scoreLimit = 120;
        SQLite_Tables.add(StockBasic);

/*        ts_code	str	TS代码
        symbol	    str	股票代码
        name	     str	股票名称
        area	    str	所在地域
        industry	str	所属行业
        fullname	str	股票全称
        enname	     str	英文全称
        market	     str	市场类型 （主板/中小板/创业板/科创板）
        exchange	str	交易所代码
        curr_type	str	交易货币
        list_status	str	上市状态： L上市 D退市 P暂停上市
        list_date	 str	上市日期
        delist_date	 str	退市日期
        is_hs	     str	是否沪深港通标的，N否 H沪股通 S深股通*/

    }


    static {
        // 【table_2_trade_cal】  交易日历 trade_cal
        //     RequestBody: {"api_name": "trade_cal", "token": "43acb9a5ddc2cf73c6c4ea54796748f965457ed57daaa736bb778ea2","params": {"exchange":"","start_date":"","end_date":"","is_open":""},"fields": "exchange,cal_date,is_open,pretrade_date"}

        DB_Table TradeCal = new DB_Table("trade_cal");
        Table_Input_Param TradeCal_exchange = new Table_Input_Param("exchange", "str", "");
        TradeCal_exchange.desc = "【交易所】 [SSE上交所] [SZSE深交所]";
        Table_Input_Param TradeCal_start_date = new Table_Input_Param("start_date", "str", "");
        TradeCal_start_date.desc = "开始日期";
        Table_Input_Param TradeCal_end_date = new Table_Input_Param("end_date", "str", "");
        TradeCal_end_date.desc = "结束日期";
        Table_Input_Param TradeCal_is_open = new Table_Input_Param("is_open", "str", "");
        TradeCal_is_open.desc = "【是否交易】 [0休市] [1交易]";
        TradeCal.addTableInputParam(TradeCal_exchange);
        TradeCal.addTableInputParam(TradeCal_start_date);
        TradeCal.addTableInputParam(TradeCal_end_date);
        TradeCal.addTableInputParam(TradeCal_is_open);


        Table_Item TradeCal_exchange_out = new Table_Item("exchange", SQLITE_TYPE.TEXT, "【交易所】 [SSE上交所] [SZSE深交所]");
        Table_Item TradeCal_cal_date_out = new Table_Item("cal_date", SQLITE_TYPE.TEXT, "日历日期");
        Table_Item TradeCal_is_open_out = new Table_Item("is_open", SQLITE_TYPE.TEXT, "是否交易");
        Table_Item TradeCal_pretrade_date_out = new Table_Item("pretrade_date", SQLITE_TYPE.TEXT, "上一个交易日");
        TradeCal.addTableItem(TradeCal_exchange_out);
        TradeCal.addTableItem(TradeCal_cal_date_out);
        TradeCal.addTableItem(TradeCal_is_open_out);
        TradeCal.addTableItem(TradeCal_pretrade_date_out);


        TradeCal.tableIndex = 2;
        TradeCal.viceTableIndex = 1;
        TradeCal.scoreLimit = 120;
//        SQLite_Tables.add(TradeCal);

    }

    static {
        //【table_3_namechange】  曾用名

        DB_Table NameChange = new DB_Table("namechange");
        Table_Input_Param NameChange_ts_code = new Table_Input_Param("ts_code", "str", "");
        NameChange_ts_code.desc = "TS代码";
        Table_Input_Param NameChange_start_date = new Table_Input_Param("start_date", "str", "");
        NameChange_start_date.desc = "开始日期";
        Table_Input_Param NameChange_end_date = new Table_Input_Param("end_date", "str", "");
        NameChange_end_date.desc = "结束日期";
        NameChange.addTableInputParam(NameChange_ts_code);
        NameChange.addTableInputParam(NameChange_start_date);
        NameChange.addTableInputParam(NameChange_end_date);

        Table_Item NameChange_ts_code_out = new Table_Item("ts_code", SQLITE_TYPE.TEXT, "TS代码");
        Table_Item NameChange_name_out = new Table_Item("name", SQLITE_TYPE.TEXT, "证券名称");
        Table_Item NameChange_start_date_out = new Table_Item("start_date", SQLITE_TYPE.TEXT, "开始日期");
        Table_Item NameChange_end_date_out = new Table_Item("end_date", SQLITE_TYPE.TEXT, "结束日期");
        Table_Item NameChange_ann_date_out = new Table_Item("ann_date", SQLITE_TYPE.TEXT, "公告日期");
        Table_Item NameChange_change_reason_out = new Table_Item("change_reason", SQLITE_TYPE.TEXT, "变更原因");


        NameChange.addTableItem(NameChange_ts_code_out);
        NameChange.addTableItem(NameChange_name_out);
        NameChange.addTableItem(NameChange_start_date_out);
        NameChange.addTableItem(NameChange_end_date_out);
        NameChange.addTableItem(NameChange_ann_date_out);
        NameChange.addTableItem(NameChange_change_reason_out);

        NameChange.tableIndex = 3;
        NameChange.viceTableIndex = 1;
//        SQLite_Tables.add(NameChange);


    }

    static {
        //【table_4_1_hs_const】   沪股通成份股
        //【table_4_2】   深股通成份股
        DB_Table Hu_GuTong = new DB_Table("hs_const");
        DB_Table Shen_GuTong = new DB_Table("hs_const");
        Table_Input_Param GuTong_hs_type_Hu = new Table_Input_Param("hs_type", "str", "SH");
        GuTong_hs_type_Hu.isMustParam = true;
        GuTong_hs_type_Hu.paramValue = "SH";
        GuTong_hs_type_Hu.desc = "【类型】[SH沪股通] [SZ深股通]";

        Table_Input_Param GuTong_hs_type_Shen = new Table_Input_Param("hs_type", "str", "SZ");
        GuTong_hs_type_Shen.isMustParam = true;
        GuTong_hs_type_Shen.paramValue = "SZ";
        GuTong_hs_type_Shen.desc = "【类型】[SH沪股通] [SZ深股通]";

        Table_Input_Param GuTong_is_new = new Table_Input_Param("is_new", "str", "");
        GuTong_is_new.desc = "[是否最新] 1是  0否 (默认1)";

        Hu_GuTong.addTableInputParam(GuTong_hs_type_Hu);
        Hu_GuTong.addTableInputParam(GuTong_is_new);

        Shen_GuTong.addTableInputParam(GuTong_hs_type_Shen);
        Shen_GuTong.addTableInputParam(GuTong_is_new);


        Table_Item GuTong_ts_code_out = new Table_Item("ts_code", SQLITE_TYPE.TEXT, "TS代码");
        Table_Item GuTong_hs_type_out = new Table_Item("hs_type", SQLITE_TYPE.TEXT, "【沪深港通类型】[SH沪] [SZ深]");
        Table_Item GuTong_in_date_out = new Table_Item("in_date", SQLITE_TYPE.TEXT, "纳入日期");
        Table_Item GuTong_out_date_out = new Table_Item("out_date", SQLITE_TYPE.TEXT, "剔除日期");
        Table_Item GuTong_is_new_out = new Table_Item("is_new", SQLITE_TYPE.TEXT, "【是否最新】 1是 0否");

        Hu_GuTong.addTableItem(GuTong_ts_code_out);
        Hu_GuTong.addTableItem(GuTong_hs_type_out);
        Hu_GuTong.addTableItem(GuTong_in_date_out);
        Hu_GuTong.addTableItem(GuTong_out_date_out);
        Hu_GuTong.addTableItem(GuTong_is_new_out);

        Shen_GuTong.addTableItem(GuTong_ts_code_out);
        Shen_GuTong.addTableItem(GuTong_hs_type_out);
        Shen_GuTong.addTableItem(GuTong_in_date_out);
        Shen_GuTong.addTableItem(GuTong_out_date_out);
        Shen_GuTong.addTableItem(GuTong_is_new_out);

        Hu_GuTong.tableIndex = 4;
        Hu_GuTong.viceTableIndex = 1;
        Shen_GuTong.tableIndex = 4;
        Shen_GuTong.viceTableIndex = 2;

//        SQLite_Tables.add(Hu_GuTong);
//        SQLite_Tables.add(Shen_GuTong);


    }

    static {
        //【table_5_stock_company】   stock_company  上市公司基本信息  积分大于120 调用
        // 数值的 段落   无法 通过  排序排查  只能通过 字符的方式 查询  需要修正   问题点1


//        {"api_name": "stock_company", "token": "43acb9a5ddc2cf73c6c4ea54796748f965457ed57daaa736bb778ea2","params": {"exchange":"SSE"},"fields": "ts_code,exchange,chairman,manager,secretary,reg_capital,setup_date,province,city,website,email,office,employees,main_business,business_scope"}


        DB_Table Stock_Company_Hu = new DB_Table("stock_company");
        DB_Table Stock_Company_Shen = new DB_Table("stock_company");

        Table_Input_Param Stock_Company_Hu_exchange = new Table_Input_Param("exchange", "str", "SSE");
        Stock_Company_Hu_exchange.desc = "【交易所代码】[SSE上交所] [SZSE深交所] [默认SSE]";
        Stock_Company_Hu.addTableInputParam(Stock_Company_Hu_exchange);

        Table_Input_Param Stock_Company_Shen_exchange = new Table_Input_Param("exchange", "str", "SZSE");
        Stock_Company_Shen_exchange.desc = "【交易所代码】[SSE上交所] [SZSE深交所] [默认SSE]";
        Stock_Company_Shen.addTableInputParam(Stock_Company_Shen_exchange);


        Table_Item Stock_Company_ts_code_out = new Table_Item("ts_code", SQLITE_TYPE.TEXT, "TS代码");
        Table_Item Stock_Company_exchange_out = new Table_Item("exchange", SQLITE_TYPE.TEXT, "交易所代码 SSE上交所 SZSE深交所");
        Table_Item Stock_Company_chairman_out = new Table_Item("chairman", SQLITE_TYPE.TEXT, "法人代表");
        Table_Item Stock_Company_manager_out = new Table_Item("manager", SQLITE_TYPE.TEXT, "总经理");
        Table_Item Stock_Company_secretary_out = new Table_Item("secretary", SQLITE_TYPE.TEXT, "董秘");
        Table_Item Stock_Company_reg_capital_out = new Table_Item("reg_capital", SQLITE_TYPE.REAL, "注册资本");
        Table_Item Stock_Company_setup_date_out = new Table_Item("setup_date", SQLITE_TYPE.TEXT, "注册日期");
        Table_Item Stock_Company_province_out = new Table_Item("province", SQLITE_TYPE.TEXT, "所在省份");
        Table_Item Stock_Company_city_out = new Table_Item("city", SQLITE_TYPE.TEXT, "所在城市");
        Table_Item Stock_Company_introduction_out = new Table_Item("introduction", SQLITE_TYPE.TEXT, "公司介绍");
        Table_Item Stock_Company_website_out = new Table_Item("website", SQLITE_TYPE.TEXT, "公司主页");
        Table_Item Stock_Company_email_out = new Table_Item("email", SQLITE_TYPE.TEXT, "电子邮件");
        Table_Item Stock_Company_office_out = new Table_Item("office", SQLITE_TYPE.TEXT, "办公室");
        Table_Item Stock_Company_employees_out = new Table_Item("employees", SQLITE_TYPE.INT, "员工人数");
        Table_Item Stock_Company_main_business_out = new Table_Item("main_business", SQLITE_TYPE.TEXT, "主要业务及产品");
        Table_Item Stock_Company_business_scope_out = new Table_Item("business_scope", SQLITE_TYPE.TEXT, "经营范围");


        Stock_Company_Hu.addTableItem(Stock_Company_ts_code_out);
        Stock_Company_Hu.addTableItem(Stock_Company_exchange_out);
        Stock_Company_Hu.addTableItem(Stock_Company_chairman_out);
        Stock_Company_Hu.addTableItem(Stock_Company_manager_out);
        Stock_Company_Hu.addTableItem(Stock_Company_secretary_out);
        Stock_Company_Hu.addTableItem(Stock_Company_reg_capital_out);
        Stock_Company_Hu.addTableItem(Stock_Company_setup_date_out);
        Stock_Company_Hu.addTableItem(Stock_Company_province_out);
        Stock_Company_Hu.addTableItem(Stock_Company_city_out);
        // 加入之后  数据太大 返回的字符串长度 1048576  超过限制 导致返回数据不全 无法解析成json   问题点2
        //   Stock_Company_Hu.addTableItem(Stock_Company_introduction_out);
        Stock_Company_Hu.addTableItem(Stock_Company_website_out);
        Stock_Company_Hu.addTableItem(Stock_Company_email_out);
        Stock_Company_Hu.addTableItem(Stock_Company_office_out);
        Stock_Company_Hu.addTableItem(Stock_Company_employees_out);
        // Stock_Company_Hu.addTableItem(Stock_Company_main_business_out);
        // 加入之后  数据太大 返回的字符串长度 1048576  超过限制 导致返回数据不全 无法解析成json
        // Stock_Company_Hu.addTableItem(Stock_Company_business_scope_out);


        Stock_Company_Shen.addTableItem(Stock_Company_ts_code_out);
        Stock_Company_Shen.addTableItem(Stock_Company_exchange_out);
        Stock_Company_Shen.addTableItem(Stock_Company_chairman_out);
        Stock_Company_Shen.addTableItem(Stock_Company_manager_out);
        Stock_Company_Shen.addTableItem(Stock_Company_secretary_out);
        Stock_Company_Shen.addTableItem(Stock_Company_reg_capital_out);
        Stock_Company_Shen.addTableItem(Stock_Company_setup_date_out);
        Stock_Company_Shen.addTableItem(Stock_Company_province_out);
        Stock_Company_Shen.addTableItem(Stock_Company_city_out);
        // 加入之后  数据太大 返回的字符串长度 1048576  超过限制 导致返回数据不全 无法解析成json
        //     Stock_Company_Shen.addTableItem(Stock_Company_introduction_out);
        Stock_Company_Shen.addTableItem(Stock_Company_website_out);
        Stock_Company_Shen.addTableItem(Stock_Company_email_out);
        Stock_Company_Shen.addTableItem(Stock_Company_office_out);
        Stock_Company_Shen.addTableItem(Stock_Company_employees_out);
        //  Stock_Company_Shen.addTableItem(Stock_Company_main_business_out);
        // 加入之后  数据太大 返回的字符串长度 1048576  超过限制 导致返回数据不全 无法解析成json
        //  Stock_Company_Shen.addTableItem(Stock_Company_business_scope_out);


        Stock_Company_Hu.tableIndex = 5;
        Stock_Company_Hu.viceTableIndex = 1;
        Stock_Company_Shen.tableIndex = 5;
        Stock_Company_Shen.viceTableIndex = 2;

//        SQLite_Tables.add(Stock_Company_Hu);
//        SQLite_Tables.add(Stock_Company_Shen);


    }


    static {
//  【 table_6_stk_managers】 接口 stk_managers：   获取上市公司管理层 // 积分2000
        DB_Table Stk_Managers = new DB_Table("stk_managers");
        Stk_Managers.tableIndex = 6;
        Stk_Managers.viceTableIndex = 1;
        Stk_Managers.scoreLimit = 2000;
        Stk_Managers.tableDesc = "获取上市公司管理层";
        Stk_Managers.request_need_tscode = true;
        Table_Input_Param Stk_Managers_ts_code = new Table_Input_Param("ts_code", "str", "");
        Stk_Managers_ts_code.desc = "股票代码支持单个或多个股票输入";
        Stk_Managers.addTableInputParam(Stk_Managers_ts_code);

        Table_Item Stk_Managers_ts_code_out = new Table_Item("ts_code", SQLITE_TYPE.TEXT, "股票代码");
        Table_Item Stk_Managers_ann_date_out = new Table_Item("ann_date", SQLITE_TYPE.TEXT, "公告日期");
        Table_Item Stk_Managers_name_out = new Table_Item("name", SQLITE_TYPE.TEXT, "姓名");
        Table_Item Stk_Managers_gender_out = new Table_Item("gender", SQLITE_TYPE.TEXT, "性别");
        Table_Item Stk_Managers_lev_out = new Table_Item("lev", SQLITE_TYPE.TEXT, "岗位类别");
        Table_Item Stk_Managers_title_out = new Table_Item("title", SQLITE_TYPE.TEXT, "岗位");
        Table_Item Stk_Managers_edu_out = new Table_Item("edu", SQLITE_TYPE.TEXT, "学历");
        Table_Item Stk_Managers_national_out = new Table_Item("national", SQLITE_TYPE.TEXT, "国籍");
        Table_Item Stk_Managers_birthday_out = new Table_Item("birthday", SQLITE_TYPE.TEXT, "出生年月");
        Table_Item Stk_Managers_begin_date_out = new Table_Item("begin_date", SQLITE_TYPE.TEXT, "上任日期");
        Table_Item Stk_Managers_end_date_out = new Table_Item("end_date", SQLITE_TYPE.TEXT, "离任日期");
        Table_Item Stk_Managers_resume_out = new Table_Item("resume", SQLITE_TYPE.TEXT, "个人简历");
        Stk_Managers.addTableItem(Stk_Managers_ts_code_out);
        Stk_Managers.addTableItem(Stk_Managers_ann_date_out);
        Stk_Managers.addTableItem(Stk_Managers_name_out);
        Stk_Managers.addTableItem(Stk_Managers_gender_out);
        Stk_Managers.addTableItem(Stk_Managers_lev_out);
        Stk_Managers.addTableItem(Stk_Managers_title_out);
        Stk_Managers.addTableItem(Stk_Managers_edu_out);
        Stk_Managers.addTableItem(Stk_Managers_national_out);
        Stk_Managers.addTableItem(Stk_Managers_birthday_out);
        Stk_Managers.addTableItem(Stk_Managers_begin_date_out);
        Stk_Managers.addTableItem(Stk_Managers_end_date_out);
        Stk_Managers.addTableItem(Stk_Managers_resume_out);

//        SQLite_Tables.add(Stk_Managers);
    }


    static {
//  【 table_7_stk_rewards 】  接口 stk_rewards  获取上市公司管理层薪酬和持股  // 积分2000
        DB_Table Stk_Rewards = new DB_Table("stk_rewards");
        Stk_Rewards.tableIndex = 7;
        Stk_Rewards.viceTableIndex = 1;
        Stk_Rewards.scoreLimit = 2000;
        Stk_Rewards.tableDesc = "获取上市公司管理层薪酬和持股";
        Stk_Rewards.request_need_tscode = true;
        Table_Input_Param Stk_Rewards_ts_code = new Table_Input_Param("ts_code", "str", "");
        Stk_Rewards_ts_code.desc = "TS股票代码，支持单个或多个代码输入";
        Table_Input_Param Stk_Rewards_end_date = new Table_Input_Param("end_date", "str", "");
        Stk_Rewards_end_date.desc = "报告期";

        Stk_Rewards.addTableInputParam(Stk_Rewards_ts_code);
        Stk_Rewards.addTableInputParam(Stk_Rewards_end_date);

        Table_Item Stk_Rewards_ts_code_out = new Table_Item("ts_code", SQLITE_TYPE.TEXT, "股票代码");
        Table_Item Stk_Rewards_ann_date_out = new Table_Item("ann_date", SQLITE_TYPE.TEXT, "公告日期");
        Table_Item Stk_Rewards_end_date_out = new Table_Item("end_date", SQLITE_TYPE.TEXT, "截止日期");
        Table_Item Stk_Rewards_name_out = new Table_Item("name", SQLITE_TYPE.TEXT, "姓名");
        Table_Item Stk_Rewards_title_out = new Table_Item("title", SQLITE_TYPE.TEXT, "职务");
        Table_Item Stk_Rewards_reward_out = new Table_Item("reward", SQLITE_TYPE.REAL, "报酬");
        Table_Item Stk_Rewards_hold_vol_out = new Table_Item("hold_vol", SQLITE_TYPE.REAL, "持股数");
        Stk_Rewards.addTableItem(Stk_Rewards_ts_code_out);
        Stk_Rewards.addTableItem(Stk_Rewards_ann_date_out);
        Stk_Rewards.addTableItem(Stk_Rewards_end_date_out);
        Stk_Rewards.addTableItem(Stk_Rewards_name_out);
        Stk_Rewards.addTableItem(Stk_Rewards_title_out);
        Stk_Rewards.addTableItem(Stk_Rewards_reward_out);
        Stk_Rewards.addTableItem(Stk_Rewards_hold_vol_out);

        SQLite_Tables.add(Stk_Rewards);
    }


    static {
        //【table_8_new_share】   new_share  上市公司基本信息  积分大于120 调用 // 积分2000
//        获取新股上市列表数据
//        限量：单次最大2000条，总量不限制

        DB_Table New_Share = new DB_Table("new_share");
        DB_Table New_Share_1 = new DB_Table("new_share");
        Table_Input_Param New_Share_start_date = new Table_Input_Param("start_date", "str", "");
        New_Share_start_date.desc = "开始日期";
        Table_Input_Param New_Share_end_date = new Table_Input_Param("end_date", "str", "");
        New_Share_end_date.desc = "结束日期";


        Table_Input_Param New_Share_end_date_1 = new Table_Input_Param("end_date", "str", "20100201");
        New_Share_end_date.desc = "结束日期";


        New_Share.addTableInputParam(New_Share_start_date);
        New_Share.addTableInputParam(New_Share_end_date);


        New_Share_1.addTableInputParam(New_Share_start_date);
        New_Share_1.addTableInputParam(New_Share_end_date_1);


        Table_Item New_Share_business_scope_out = new Table_Item("ts_code", SQLITE_TYPE.TEXT, "TS股票代码");
        Table_Item New_Share_sub_code_out = new Table_Item("sub_code", SQLITE_TYPE.TEXT, "申购代码");
        Table_Item New_Share_name_out = new Table_Item("name", SQLITE_TYPE.TEXT, "名称");
        Table_Item New_Share_ipo_date_out = new Table_Item("ipo_date", SQLITE_TYPE.TEXT, "上网发行日期");
        Table_Item New_Share_issue_date_out = new Table_Item("issue_date	", SQLITE_TYPE.TEXT, "上市日期");
        Table_Item New_Share_amount_out = new Table_Item("amount", SQLITE_TYPE.REAL, "发行总量（万股）");
        Table_Item New_Share_market_amount_out = new Table_Item("market_amount", SQLITE_TYPE.REAL, "上网发行总量（万股）");
        Table_Item New_Share_price_out = new Table_Item("price", SQLITE_TYPE.REAL, "发行价格");
        Table_Item New_Share_pe_out = new Table_Item("pe	", SQLITE_TYPE.REAL, "市盈率");
        Table_Item New_Share_limit_amount_out = new Table_Item("limit_amount", SQLITE_TYPE.REAL, "个人申购上限（万股）");
        Table_Item New_Share_funds_out = new Table_Item("funds", SQLITE_TYPE.REAL, "募集资金（亿元）");
        Table_Item New_Share_ballot_out = new Table_Item("ballot	", SQLITE_TYPE.REAL, "中签率");

        New_Share.addTableItem(New_Share_business_scope_out);
        New_Share.addTableItem(New_Share_sub_code_out);
        New_Share.addTableItem(New_Share_name_out);
        New_Share.addTableItem(New_Share_ipo_date_out);
        New_Share.addTableItem(New_Share_issue_date_out);
        New_Share.addTableItem(New_Share_amount_out);
        New_Share.addTableItem(New_Share_market_amount_out);
        New_Share.addTableItem(New_Share_price_out);
        New_Share.addTableItem(New_Share_pe_out);
        New_Share.addTableItem(New_Share_limit_amount_out);
        New_Share.addTableItem(New_Share_funds_out);
        New_Share.addTableItem(New_Share_ballot_out);


        New_Share_1.addTableItem(New_Share_business_scope_out);
        New_Share_1.addTableItem(New_Share_sub_code_out);
        New_Share_1.addTableItem(New_Share_name_out);
        New_Share_1.addTableItem(New_Share_ipo_date_out);
        New_Share_1.addTableItem(New_Share_issue_date_out);
        New_Share_1.addTableItem(New_Share_amount_out);
        New_Share_1.addTableItem(New_Share_market_amount_out);
        New_Share_1.addTableItem(New_Share_price_out);
        New_Share_1.addTableItem(New_Share_pe_out);
        New_Share_1.addTableItem(New_Share_limit_amount_out);
        New_Share_1.addTableItem(New_Share_funds_out);
        New_Share_1.addTableItem(New_Share_ballot_out);

        New_Share.tableIndex = 8;
        New_Share.viceTableIndex = 1;
        New_Share_1.tableIndex = 8;
        New_Share_1.viceTableIndex = 2;

//        SQLite_Tables.add(New_Share);
//        SQLite_Tables.add(New_Share_1);

    }


    static {
        // 【Table_9_daily】   daily  日线行情
        // //
        DB_Table Daily = new DB_Table("daily");
        Daily.tableIndex = 9;
        Daily.viceTableIndex = 1;
        Daily.tableDesc = "日线行情数据";


        Table_Input_Param Daily_ts_code = new Table_Input_Param("ts_code", "str", "");
        Daily_ts_code.desc = "TS代码";
        Table_Input_Param Daily_trade_date = new Table_Input_Param("trade_date", "str", "");
        Daily_trade_date.desc = "交易日期";

        Table_Input_Param Daily_start_date = new Table_Input_Param("start_date", "str", "");
        Daily_start_date.desc = "开始日期";
        Table_Input_Param Daily_end_date = new Table_Input_Param("end_date", "str", "");
        Daily_end_date.desc = "结束日期";

        Daily.addTableInputParam(Daily_ts_code);
        Daily.addTableInputParam(Daily_trade_date);
        Daily.addTableInputParam(Daily_start_date);
        Daily.addTableInputParam(Daily_end_date);


        Table_Item Daily_ts_code_out = new Table_Item("ts_code", SQLITE_TYPE.TEXT, "TS股票代码");
        Table_Item Daily_trade_date_out = new Table_Item("trade_date", SQLITE_TYPE.TEXT, "交易日期");
        Table_Item Daily_open_out = new Table_Item("open", SQLITE_TYPE.REAL, "开盘价");
        Table_Item Daily_high_out = new Table_Item("high", SQLITE_TYPE.REAL, "最高价");
        Table_Item Daily_low_out = new Table_Item("low", SQLITE_TYPE.REAL, "最低价");
        Table_Item Daily_close_out = new Table_Item("close", SQLITE_TYPE.REAL, "收盘价");
        Table_Item Daily_pre_close_out = new Table_Item("pre_close", SQLITE_TYPE.REAL, "昨收盘价");
        Table_Item Daily_change_out = new Table_Item("change", SQLITE_TYPE.REAL, "涨跌额");
        Table_Item Daily_pct_chg_out = new Table_Item("pct_chg", SQLITE_TYPE.REAL, "涨跌幅(未复权)");
        Table_Item Daily_vol_out = new Table_Item("vol", SQLITE_TYPE.REAL, "成交量 （手）");
        Table_Item Daily_amount_out = new Table_Item("amount", SQLITE_TYPE.REAL, "成交额(千元)");


        Daily.addTableItem(Daily_ts_code_out);
        Daily.addTableItem(Daily_trade_date_out);
        Daily.addTableItem(Daily_open_out);
        Daily.addTableItem(Daily_high_out);
        Daily.addTableItem(Daily_low_out);
        Daily.addTableItem(Daily_close_out);
        Daily.addTableItem(Daily_pre_close_out);
        Daily.addTableItem(Daily_change_out);
        Daily.addTableItem(Daily_pct_chg_out);
        Daily.addTableItem(Daily_vol_out);
        Daily.addTableItem(Daily_amount_out);

//        SQLite_Tables.add(Daily);


    }

    static {
//  【 table_10_weekly】 获取A股周线行情
        DB_Table Weekly = new DB_Table("weekly");
        Weekly.tableIndex = 10;
        Weekly.viceTableIndex = 1;
        Weekly.scoreLimit = 300;
        Weekly.tableDesc = "获10取A股周线行情";
        Table_Input_Param Weekly_trade_date = new Table_Input_Param("trade_date", "str", "");
        Weekly_trade_date.desc = "交易日期每周五日期格式";
        Table_Input_Param Weekly_start_date = new Table_Input_Param("start_date", "str", "");
        Weekly_start_date.desc = "开始日期";
        Table_Input_Param Weekly_end_date = new Table_Input_Param("end_date", "str", "");
        Weekly_end_date.desc = "结束日期";
        Weekly.addTableInputParam(Weekly_trade_date);
        Weekly.addTableInputParam(Weekly_start_date);
        Weekly.addTableInputParam(Weekly_end_date);

        Table_Item Weekly_ts_code_out = new Table_Item("ts_code", SQLITE_TYPE.TEXT, "股票代码");
        Table_Item Weekly_trade_date_out = new Table_Item("trade_date", SQLITE_TYPE.TEXT, "交易日期");
        Table_Item Weekly_close_out = new Table_Item("close", SQLITE_TYPE.REAL, "周收盘价");
        Table_Item Weekly_open_out = new Table_Item("open", SQLITE_TYPE.REAL, "周开盘价");
        Table_Item Weekly_high_out = new Table_Item("high", SQLITE_TYPE.REAL, "周最高价");
        Table_Item Weekly_low_out = new Table_Item("low", SQLITE_TYPE.REAL, "周最低价");
        Table_Item Weekly_pre_close_out = new Table_Item("pre_close", SQLITE_TYPE.REAL, "上一周收盘价");
        Table_Item Weekly_change_out = new Table_Item("change", SQLITE_TYPE.REAL, "周涨跌额");
        Table_Item Weekly_vol_out = new Table_Item("vol", SQLITE_TYPE.REAL, "周成交量");
        Table_Item Weekly_amount_out = new Table_Item("amount", SQLITE_TYPE.REAL, "周成交额");
        Weekly.addTableItem(Weekly_ts_code_out);
        Weekly.addTableItem(Weekly_trade_date_out);
        Weekly.addTableItem(Weekly_close_out);
        Weekly.addTableItem(Weekly_open_out);
        Weekly.addTableItem(Weekly_high_out);
        Weekly.addTableItem(Weekly_low_out);
        Weekly.addTableItem(Weekly_pre_close_out);
        Weekly.addTableItem(Weekly_change_out);
        Weekly.addTableItem(Weekly_vol_out);
        Weekly.addTableItem(Weekly_amount_out);

        SQLite_Tables.add(Weekly);
    }


    static {
//  【 table_11_monthly 】 获取A股月线数据
        DB_Table Monthly = new DB_Table("monthly");
        Monthly.tableIndex = 11;
        Monthly.viceTableIndex = 1;
        Monthly.scoreLimit = 300;
        Monthly.tableDesc = "获取A股月线数据";
        Table_Input_Param Monthly_trade_date = new Table_Input_Param("trade_date", "str", "");
        Monthly_trade_date.desc = "交易日期每月最后一个交易日日期格式";
        Table_Input_Param Monthly_start_date = new Table_Input_Param("start_date", "str", "");
        Monthly_start_date.desc = "开始日期";
        Table_Input_Param Monthly_end_date = new Table_Input_Param("end_date", "str", "");
        Monthly_end_date.desc = "结束日期";
        Monthly.addTableInputParam(Monthly_trade_date);
        Monthly.addTableInputParam(Monthly_start_date);
        Monthly.addTableInputParam(Monthly_end_date);

        Table_Item Monthly_ts_code_out = new Table_Item("ts_code", SQLITE_TYPE.TEXT, "股票代码");
        Table_Item Monthly_trade_date_out = new Table_Item("trade_date", SQLITE_TYPE.TEXT, "交易日期");
        Table_Item Monthly_close_out = new Table_Item("close", SQLITE_TYPE.REAL, "月收盘价");
        Table_Item Monthly_open_out = new Table_Item("open", SQLITE_TYPE.REAL, "月开盘价");
        Table_Item Monthly_high_out = new Table_Item("high", SQLITE_TYPE.REAL, "月最高价");
        Table_Item Monthly_low_out = new Table_Item("low", SQLITE_TYPE.REAL, "月最低价");
        Table_Item Monthly_pre_close_out = new Table_Item("pre_close", SQLITE_TYPE.REAL, "上月收盘价");
        Table_Item Monthly_change_out = new Table_Item("change", SQLITE_TYPE.REAL, "月涨跌额");
        Table_Item Monthly_pct_chg_out = new Table_Item("pct_chg", SQLITE_TYPE.REAL, "月涨跌幅未复权如果是复权请用通用行情接口");
        Table_Item Monthly_vol_out = new Table_Item("vol", SQLITE_TYPE.REAL, "月成交量");
        Table_Item Monthly_amount_out = new Table_Item("amount", SQLITE_TYPE.REAL, "月成交额");
        Monthly.addTableItem(Monthly_ts_code_out);
        Monthly.addTableItem(Monthly_trade_date_out);
        Monthly.addTableItem(Monthly_close_out);
        Monthly.addTableItem(Monthly_open_out);
        Monthly.addTableItem(Monthly_high_out);
        Monthly.addTableItem(Monthly_low_out);
        Monthly.addTableItem(Monthly_pre_close_out);
        Monthly.addTableItem(Monthly_change_out);
        Monthly.addTableItem(Monthly_pct_chg_out);
        Monthly.addTableItem(Monthly_vol_out);
        Monthly.addTableItem(Monthly_amount_out);

        SQLite_Tables.add(Monthly);
    }


    static {
        // 【Table_12】   adj_factor  复权因子
        //
        DB_Table Adj_Factor = new DB_Table("adj_factor");

        Adj_Factor.tableIndex = 12;
        Adj_Factor.viceTableIndex = 1;
        Adj_Factor.tableDesc = "复权因子数据";


        Table_Input_Param Adj_Factor_ts_code = new Table_Input_Param("ts_code", "str", "");
        Adj_Factor_ts_code.desc = "TS代码";

        Table_Input_Param Adj_Factor_trade_date = new Table_Input_Param("resume_date", "str", "");
        Adj_Factor_trade_date.desc = "复牌日期";

        Table_Input_Param Adj_Factor_start_date = new Table_Input_Param("suspend_date", "str", "");
        Adj_Factor_start_date.desc = "停牌日期";
        Table_Input_Param Adj_Factor_end_date = new Table_Input_Param("suspend_date", "str", "");
        Adj_Factor_end_date.desc = "停牌日期";


        Adj_Factor.addTableInputParam(Adj_Factor_ts_code);
        Adj_Factor.addTableInputParam(Adj_Factor_trade_date);
        Adj_Factor.addTableInputParam(Adj_Factor_start_date);
        Adj_Factor.addTableInputParam(Adj_Factor_end_date);


        Table_Item Adj_Factor_ts_code_out = new Table_Item("ts_code", SQLITE_TYPE.TEXT, "股票代码");
        Table_Item Adj_Factor_trade_date_out = new Table_Item("trade_date", SQLITE_TYPE.TEXT, "交易日期");
        Table_Item Adj_Factor_adj_factor_out = new Table_Item("adj_factor", SQLITE_TYPE.REAL, "复权因子");


        Adj_Factor.addTableItem(Adj_Factor_ts_code_out);
        Adj_Factor.addTableItem(Adj_Factor_trade_date_out);
        Adj_Factor.addTableItem(Adj_Factor_adj_factor_out);

//        SQLite_Tables.add(Adj_Factor);

    }


    static {
        // 【Table_13_suspend】   suspend  停复牌信息
        //

        DB_Table Suspend = new DB_Table("suspend");

        Suspend.tableIndex = 13;
        Suspend.viceTableIndex = 1;
        DB_Table Suspend_2 = new DB_Table("suspend");
        Suspend_2.tableIndex = 13;
        Suspend_2.viceTableIndex = 2;

        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();
//        String dateNameNow = df.format(calendar.getTime());
        String dateNameNow = "20191018";
        Table_Input_Param Suspend_ts_code = new Table_Input_Param("ts_code", "str", "");
        Suspend_ts_code.desc = "TS代码";
        Table_Input_Param Suspend_start_date = new Table_Input_Param("suspend_date", "str", dateNameNow);
        Suspend_start_date.desc = "停牌日期";
        Table_Input_Param Suspend_end_date = new Table_Input_Param("resume_date", "str", "");
        Suspend_end_date.desc = "复牌日期";

        Suspend.addTableInputParam(Suspend_ts_code);
        Suspend.addTableInputParam(Suspend_start_date);
        Suspend.addTableInputParam(Suspend_end_date);

        Table_Input_Param Suspend_start_date_1 = new Table_Input_Param("suspend_date", "str", "");
        Suspend_start_date.desc = "停牌日期";
        Table_Input_Param Suspend_end_date_1 = new Table_Input_Param("resume_date", "str", dateNameNow);
        Suspend_end_date_1.desc = "复牌日期";


        Suspend_2.addTableInputParam(Suspend_ts_code);
        Suspend_2.addTableInputParam(Suspend_start_date_1);
        Suspend_2.addTableInputParam(Suspend_end_date_1);


        Table_Item Suspend_ts_code_out = new Table_Item("ts_code", SQLITE_TYPE.TEXT, "股票代码");
        Table_Item Suspend_suspend_date_out = new Table_Item("suspend_date", SQLITE_TYPE.TEXT, "停牌日期");
        Table_Item Suspend_resume_date_out = new Table_Item("resume_date", SQLITE_TYPE.TEXT, "复牌日期");
        Table_Item Suspend_ann_date_out = new Table_Item("ann_date", SQLITE_TYPE.TEXT, "公告日期");
        Table_Item Suspend_suspend_reason_out = new Table_Item("suspend_reason", SQLITE_TYPE.TEXT, "停牌原因");
        Table_Item Suspend_reason_type_out = new Table_Item("reason_type", SQLITE_TYPE.TEXT, "停牌原因类别");


        Suspend.addTableItem(Suspend_ts_code_out);
        Suspend.addTableItem(Suspend_suspend_date_out);
        Suspend.addTableItem(Suspend_resume_date_out);
        Suspend.addTableItem(Suspend_ann_date_out);
        Suspend.addTableItem(Suspend_suspend_reason_out);
        Suspend.addTableItem(Suspend_reason_type_out);


        Suspend_2.addTableItem(Suspend_ts_code_out);
        Suspend_2.addTableItem(Suspend_suspend_date_out);
        Suspend_2.addTableItem(Suspend_resume_date_out);
        Suspend_2.addTableItem(Suspend_ann_date_out);
        Suspend_2.addTableItem(Suspend_suspend_reason_out);
        Suspend_2.addTableItem(Suspend_reason_type_out);

        SQLite_Tables.add(Suspend);
        SQLite_Tables.add(Suspend_2);
    }


    static {
//  【 table_14_daily_basic 】 获取全部股票每日重要的基本面指标，可用于选股分析、报表展示等。
        DB_Table Daily_Basic = new DB_Table("daily_basic");
        Daily_Basic.tableIndex = 14;
        Daily_Basic.viceTableIndex = 1;
        Daily_Basic.scoreLimit = 300;
        Daily_Basic.request_need_tscode = true;
        Daily_Basic.tableDesc = "获取全部股票每日重要的基本面指标，可用于选股分析、报表展示等。";
        Table_Input_Param Daily_Basic_ts_code = new Table_Input_Param("ts_code", "str", "");
        Daily_Basic_ts_code.desc = "股票代码二选一";
        Table_Input_Param Daily_Basic_trade_date = new Table_Input_Param("trade_date", "str", "");
        Daily_Basic_trade_date.desc = "交易日期二选一";
        Table_Input_Param Daily_Basic_start_date = new Table_Input_Param("start_date", "str", "");
        Daily_Basic_start_date.desc = "开始日期";
        Table_Input_Param Daily_Basic_end_date = new Table_Input_Param("end_date", "str", "");
        Daily_Basic_end_date.desc = "结束日期";
        Daily_Basic.addTableInputParam(Daily_Basic_ts_code);
        Daily_Basic.addTableInputParam(Daily_Basic_trade_date);
        Daily_Basic.addTableInputParam(Daily_Basic_start_date);
        Daily_Basic.addTableInputParam(Daily_Basic_end_date);

        Table_Item Daily_Basic_ts_code_out = new Table_Item("ts_code", SQLITE_TYPE.TEXT, "股票代码");
        Table_Item Daily_Basic_trade_date_out = new Table_Item("trade_date", SQLITE_TYPE.TEXT, "交易日期");
        Table_Item Daily_Basic_close_out = new Table_Item("close", SQLITE_TYPE.REAL, "当日收盘价");
        Table_Item Daily_Basic_turnover_rate_out = new Table_Item("turnover_rate", SQLITE_TYPE.REAL, "换手率");
        Table_Item Daily_Basic_turnover_rate_f_out = new Table_Item("turnover_rate_f", SQLITE_TYPE.REAL, "换手率自由流通股");
        Table_Item Daily_Basic_volume_ratio_out = new Table_Item("volume_ratio", SQLITE_TYPE.REAL, "量比");
        Table_Item Daily_Basic_pe_out = new Table_Item("pe", SQLITE_TYPE.REAL, "市盈率总市值净利润");
        Table_Item Daily_Basic_pe_ttm_out = new Table_Item("pe_ttm", SQLITE_TYPE.REAL, "市盈率");
        Table_Item Daily_Basic_pb_out = new Table_Item("pb", SQLITE_TYPE.REAL, "市净率总市值净资产");
        Table_Item Daily_Basic_ps_out = new Table_Item("ps", SQLITE_TYPE.REAL, "市销率");
        Table_Item Daily_Basic_ps_ttm_out = new Table_Item("ps_ttm", SQLITE_TYPE.REAL, "市销率");
        Table_Item Daily_Basic_total_share_out = new Table_Item("total_share", SQLITE_TYPE.REAL, "总股本万股");
        Table_Item Daily_Basic_float_share_out = new Table_Item("float_share", SQLITE_TYPE.REAL, "流通股本万股");
        Table_Item Daily_Basic_free_share_out = new Table_Item("free_share", SQLITE_TYPE.REAL, "自由流通股本万");
        Table_Item Daily_Basic_total_mv_out = new Table_Item("total_mv", SQLITE_TYPE.REAL, "总市值万元");
        Table_Item Daily_Basic_circ_mv_out = new Table_Item("circ_mv", SQLITE_TYPE.REAL, "流通市值万元");
        Daily_Basic.addTableItem(Daily_Basic_ts_code_out);
        Daily_Basic.addTableItem(Daily_Basic_trade_date_out);
        Daily_Basic.addTableItem(Daily_Basic_close_out);
        Daily_Basic.addTableItem(Daily_Basic_turnover_rate_out);
        Daily_Basic.addTableItem(Daily_Basic_turnover_rate_f_out);
        Daily_Basic.addTableItem(Daily_Basic_volume_ratio_out);
        Daily_Basic.addTableItem(Daily_Basic_pe_out);
        Daily_Basic.addTableItem(Daily_Basic_pe_ttm_out);
        Daily_Basic.addTableItem(Daily_Basic_pb_out);
        Daily_Basic.addTableItem(Daily_Basic_ps_out);
        Daily_Basic.addTableItem(Daily_Basic_ps_ttm_out);
        Daily_Basic.addTableItem(Daily_Basic_total_share_out);
        Daily_Basic.addTableItem(Daily_Basic_float_share_out);
        Daily_Basic.addTableItem(Daily_Basic_free_share_out);
        Daily_Basic.addTableItem(Daily_Basic_total_mv_out);
        Daily_Basic.addTableItem(Daily_Basic_circ_mv_out);

        SQLite_Tables.add(Daily_Basic);
    }


    static {
//  【 table_15_moneyflow 】 获取沪深A股票资金流向数据，分析大单小单成交情况，用于判别资金动向
        DB_Table Moneyflow = new DB_Table("moneyflow");
        Moneyflow.tableIndex = 15;
        Moneyflow.viceTableIndex = 1;
        Moneyflow.scoreLimit = 1500;
        Moneyflow.tableDesc = "获取沪深A股票资金流向数据，分析大单小单成交情况，用于判别资金动向";
        Table_Input_Param Moneyflow_ts_code = new Table_Input_Param("ts_code", "str", "");
        Moneyflow_ts_code.desc = "股票代码股票和时间参数至少输入一个";
        Table_Input_Param Moneyflow_trade_date = new Table_Input_Param("trade_date", "str", "");
        Moneyflow_trade_date.desc = "交易日期";
        Table_Input_Param Moneyflow_start_date = new Table_Input_Param("start_date", "str", "");
        Moneyflow_start_date.desc = "开始日期";
        Table_Input_Param Moneyflow_end_date = new Table_Input_Param("end_date", "str", "");
        Moneyflow_end_date.desc = "结束日期";
        Moneyflow.addTableInputParam(Moneyflow_ts_code);
        Moneyflow.addTableInputParam(Moneyflow_trade_date);
        Moneyflow.addTableInputParam(Moneyflow_start_date);
        Moneyflow.addTableInputParam(Moneyflow_end_date);

        Table_Item Moneyflow_ts_code_out = new Table_Item("ts_code", SQLITE_TYPE.TEXT, "代码");
        Table_Item Moneyflow_trade_date_out = new Table_Item("trade_date", SQLITE_TYPE.TEXT, "交易日期");
        Table_Item Moneyflow_buy_sm_vol_out = new Table_Item("buy_sm_vol", SQLITE_TYPE.INT, "小单买入量手");
        Table_Item Moneyflow_buy_sm_amount_out = new Table_Item("buy_sm_amount", SQLITE_TYPE.REAL, "小单买入金额万元");
        Table_Item Moneyflow_sell_sm_vol_out = new Table_Item("sell_sm_vol", SQLITE_TYPE.INT, "小单卖出量手");
        Table_Item Moneyflow_sell_sm_amount_out = new Table_Item("sell_sm_amount", SQLITE_TYPE.REAL, "小单卖出金额万元");
        Table_Item Moneyflow_buy_md_vol_out = new Table_Item("buy_md_vol", SQLITE_TYPE.INT, "中单买入量手");
        Table_Item Moneyflow_buy_md_amount_out = new Table_Item("buy_md_amount", SQLITE_TYPE.REAL, "中单买入金额万元");
        Table_Item Moneyflow_sell_md_vol_out = new Table_Item("sell_md_vol", SQLITE_TYPE.INT, "中单卖出量手");
        Table_Item Moneyflow_sell_md_amount_out = new Table_Item("sell_md_amount", SQLITE_TYPE.REAL, "中单卖出金额万元");
        Table_Item Moneyflow_buy_lg_vol_out = new Table_Item("buy_lg_vol", SQLITE_TYPE.INT, "大单买入量手");
        Table_Item Moneyflow_buy_lg_amount_out = new Table_Item("buy_lg_amount", SQLITE_TYPE.REAL, "大单买入金额万元");
        Table_Item Moneyflow_sell_lg_vol_out = new Table_Item("sell_lg_vol", SQLITE_TYPE.INT, "大单卖出量手");
        Table_Item Moneyflow_sell_lg_amount_out = new Table_Item("sell_lg_amount", SQLITE_TYPE.REAL, "大单卖出金额万元");
        Table_Item Moneyflow_buy_elg_vol_out = new Table_Item("buy_elg_vol", SQLITE_TYPE.INT, "特大单买入量手");
        Table_Item Moneyflow_buy_elg_amount_out = new Table_Item("buy_elg_amount", SQLITE_TYPE.REAL, "特大单买入金额万元");
        Table_Item Moneyflow_sell_elg_vol_out = new Table_Item("sell_elg_vol", SQLITE_TYPE.INT, "特大单卖出量手");
        Table_Item Moneyflow_sell_elg_amount_out = new Table_Item("sell_elg_amount", SQLITE_TYPE.REAL, "特大单卖出金额万元");
        Table_Item Moneyflow_net_mf_vol_out = new Table_Item("net_mf_vol", SQLITE_TYPE.INT, "净流入量手");
        Table_Item Moneyflow_net_mf_amount_out = new Table_Item("net_mf_amount", SQLITE_TYPE.REAL, "净流入额万元");
        Moneyflow.addTableItem(Moneyflow_ts_code_out);
        Moneyflow.addTableItem(Moneyflow_trade_date_out);
        Moneyflow.addTableItem(Moneyflow_buy_sm_vol_out);
        Moneyflow.addTableItem(Moneyflow_buy_sm_amount_out);
        Moneyflow.addTableItem(Moneyflow_sell_sm_vol_out);
        Moneyflow.addTableItem(Moneyflow_sell_sm_amount_out);
        Moneyflow.addTableItem(Moneyflow_buy_md_vol_out);
        Moneyflow.addTableItem(Moneyflow_buy_md_amount_out);
        Moneyflow.addTableItem(Moneyflow_sell_md_vol_out);
        Moneyflow.addTableItem(Moneyflow_sell_md_amount_out);
        Moneyflow.addTableItem(Moneyflow_buy_lg_vol_out);
        Moneyflow.addTableItem(Moneyflow_buy_lg_amount_out);
        Moneyflow.addTableItem(Moneyflow_sell_lg_vol_out);
        Moneyflow.addTableItem(Moneyflow_sell_lg_amount_out);
        Moneyflow.addTableItem(Moneyflow_buy_elg_vol_out);
        Moneyflow.addTableItem(Moneyflow_buy_elg_amount_out);
        Moneyflow.addTableItem(Moneyflow_sell_elg_vol_out);
        Moneyflow.addTableItem(Moneyflow_sell_elg_amount_out);
        Moneyflow.addTableItem(Moneyflow_net_mf_vol_out);
        Moneyflow.addTableItem(Moneyflow_net_mf_amount_out);

        SQLite_Tables.add(Moneyflow);
    }


    static {
//  【 table_16_stk_limit 】 获取全市场（包含A/B股和基金）每日涨跌停价格，包括涨停价格，跌停价格等，每个交易日8点40左右更新当日股票涨跌停价格。
        DB_Table Stk_Limit = new DB_Table("stk_limit");
        Stk_Limit.tableIndex = 16;
        Stk_Limit.viceTableIndex = 1;
        Stk_Limit.scoreLimit = 600;
        Stk_Limit.tableDesc = "获取全市场（包含A/B股和基金）每日涨跌停价格，包括涨停价格，跌停价格等，每个交易日8点40左右更新当日股票涨跌停价格。";
        Table_Input_Param Stk_Limit_ts_code = new Table_Input_Param("ts_code", "str", "");
        Stk_Limit_ts_code.desc = "股票代码";
        Table_Input_Param Stk_Limit_trade_date = new Table_Input_Param("trade_date", "str", "");
        Stk_Limit_trade_date.desc = "交易日期";
        Table_Input_Param Stk_Limit_start_date = new Table_Input_Param("start_date", "str", "");
        Stk_Limit_start_date.desc = "开始日期";
        Table_Input_Param Stk_Limit_end_date = new Table_Input_Param("end_date", "str", "");
        Stk_Limit_end_date.desc = "结束日期";
        Stk_Limit.addTableInputParam(Stk_Limit_ts_code);
        Stk_Limit.addTableInputParam(Stk_Limit_trade_date);
        Stk_Limit.addTableInputParam(Stk_Limit_start_date);
        Stk_Limit.addTableInputParam(Stk_Limit_end_date);

        Table_Item Stk_Limit_trade_date_out = new Table_Item("trade_date", SQLITE_TYPE.TEXT, "交易日期");
        Table_Item Stk_Limit_ts_code_out = new Table_Item("ts_code", SQLITE_TYPE.TEXT, "股票代码");
        Table_Item Stk_Limit_pre_close_out = new Table_Item("pre_close", SQLITE_TYPE.REAL, "昨日收盘价");
        Table_Item Stk_Limit_up_limit_out = new Table_Item("up_limit", SQLITE_TYPE.REAL, "涨停价");
        Table_Item Stk_Limit_down_limit_out = new Table_Item("down_limit", SQLITE_TYPE.REAL, "跌停价");
        Stk_Limit.addTableItem(Stk_Limit_trade_date_out);
        Stk_Limit.addTableItem(Stk_Limit_ts_code_out);
        Stk_Limit.addTableItem(Stk_Limit_pre_close_out);
        Stk_Limit.addTableItem(Stk_Limit_up_limit_out);
        Stk_Limit.addTableItem(Stk_Limit_down_limit_out);

        SQLite_Tables.add(Stk_Limit);
    }


    static {
//  【 table_17_limit_list 】 获取每日涨跌停股票统计，包括封闭时间和打开次数等数据，帮助用户快速定位近期强（弱）势股，以及研究超短线策略。
        DB_Table Limit_List = new DB_Table("limit_list");
        Limit_List.tableIndex = 17;
        Limit_List.viceTableIndex = 1;
        Limit_List.scoreLimit = 2000;
        Limit_List.tableDesc = "获取每日涨跌停股票统计，包括封闭时间和打开次数等数据，帮助用户快速定位近期强（弱）势股，以及研究超短线策略。";
        Table_Input_Param Limit_List_trade_date = new Table_Input_Param("trade_date", "str", "");
        Limit_List_trade_date.desc = "交易日期格式支持单个或多日期输入";
        Table_Input_Param Limit_List_ts_code = new Table_Input_Param("ts_code", "str", "");
        Limit_List_ts_code.desc = "股票代码支持单个或多个股票输入";
        Table_Input_Param Limit_List_limit_type = new Table_Input_Param("limit_type", "str", "");
        Limit_List_limit_type.desc = "涨跌停类型涨停跌停";
        Table_Input_Param Limit_List_start_date = new Table_Input_Param("start_date", "str", "");
        Limit_List_start_date.desc = "开始日期格式";
        Table_Input_Param Limit_List_end_date = new Table_Input_Param("end_date", "str", "");
        Limit_List_end_date.desc = "结束日期格式";
        Limit_List.addTableInputParam(Limit_List_trade_date);
        Limit_List.addTableInputParam(Limit_List_ts_code);
        Limit_List.addTableInputParam(Limit_List_limit_type);
        Limit_List.addTableInputParam(Limit_List_start_date);
        Limit_List.addTableInputParam(Limit_List_end_date);

        Table_Item Limit_List_trade_date_out = new Table_Item("trade_date", SQLITE_TYPE.TEXT, "交易日期");
        Table_Item Limit_List_ts_code_out = new Table_Item("ts_code", SQLITE_TYPE.TEXT, "股票代码");
        Table_Item Limit_List_name_out = new Table_Item("name", SQLITE_TYPE.TEXT, "股票名称");
        Table_Item Limit_List_close_out = new Table_Item("close", SQLITE_TYPE.REAL, "收盘价");
        Table_Item Limit_List_pct_chg_out = new Table_Item("pct_chg", SQLITE_TYPE.REAL, "涨跌幅");
        Table_Item Limit_List_amp_out = new Table_Item("amp", SQLITE_TYPE.REAL, "振幅");
        Table_Item Limit_List_fc_ratio_out = new Table_Item("fc_ratio", SQLITE_TYPE.REAL, "封单金额日成交金额");
        Table_Item Limit_List_fl_ratio_out = new Table_Item("fl_ratio", SQLITE_TYPE.REAL, "封单手数流通股本");
        Table_Item Limit_List_fd_amount_out = new Table_Item("fd_amount", SQLITE_TYPE.REAL, "封单金额");
        Table_Item Limit_List_first_time_out = new Table_Item("first_time", SQLITE_TYPE.TEXT, "首次涨停时间");
        Table_Item Limit_List_last_time_out = new Table_Item("last_time", SQLITE_TYPE.TEXT, "最后封板时间");
        Table_Item Limit_List_open_times_out = new Table_Item("open_times", SQLITE_TYPE.INT, "打开次数");
        Table_Item Limit_List_strth_out = new Table_Item("strth", SQLITE_TYPE.REAL, "涨跌停强度");
        Table_Item Limit_List_limit_out = new Table_Item("limit", SQLITE_TYPE.TEXT, "跌停涨停");
        Limit_List.addTableItem(Limit_List_trade_date_out);
        Limit_List.addTableItem(Limit_List_ts_code_out);
        Limit_List.addTableItem(Limit_List_name_out);
        Limit_List.addTableItem(Limit_List_close_out);
        Limit_List.addTableItem(Limit_List_pct_chg_out);
        Limit_List.addTableItem(Limit_List_amp_out);
        Limit_List.addTableItem(Limit_List_fc_ratio_out);
        Limit_List.addTableItem(Limit_List_fl_ratio_out);
        Limit_List.addTableItem(Limit_List_fd_amount_out);
        Limit_List.addTableItem(Limit_List_first_time_out);
        Limit_List.addTableItem(Limit_List_last_time_out);
        Limit_List.addTableItem(Limit_List_open_times_out);
        Limit_List.addTableItem(Limit_List_strth_out);
        Limit_List.addTableItem(Limit_List_limit_out);

        SQLite_Tables.add(Limit_List);
    }


    static {
        // 【Table_18_moneyflow_hsgt】   moneyflow_hsgt   沪深港通资金流向
        // {"api_name": "moneyflow_hsgt", "token": "43acb9a5ddc2cf73c6c4ea54796748f965457ed57daaa736bb778ea2","params": {"trade_date":"20191018","start_date":"","end_date":""},"fields": "trade_date,ggt_ss,ggt_sz,hgt,sgt,north_money,south_money"}
        DB_Table Moneyflow_Hsgt = new DB_Table("moneyflow_hsgt");

        Moneyflow_Hsgt.tableIndex = 18;
        Moneyflow_Hsgt.viceTableIndex = 1;
        Moneyflow_Hsgt.tableDesc = "沪深港通资金流向数据";


        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();
//        String dateNameNow = df.format(calendar.getTime());
        String dateNameNow = "20191018";

        Table_Input_Param Moneyflow_Hsgt_trade_date = new Table_Input_Param("trade_date", "str", dateNameNow);
        Moneyflow_Hsgt_trade_date.desc = "交易日期";

        Table_Input_Param Moneyflow_Hsgt_start_date = new Table_Input_Param("start_date", "str", "");
        Moneyflow_Hsgt_start_date.desc = "开始日期";
        Table_Input_Param Moneyflow_Hsgt_end_date = new Table_Input_Param("end_date", "str", "");
        Moneyflow_Hsgt_end_date.desc = "结束日期";


        Moneyflow_Hsgt.addTableInputParam(Moneyflow_Hsgt_trade_date);
        Moneyflow_Hsgt.addTableInputParam(Moneyflow_Hsgt_start_date);
        Moneyflow_Hsgt.addTableInputParam(Moneyflow_Hsgt_end_date);


        Table_Item Moneyflow_Hsgt_trade_date_out = new Table_Item("trade_date", SQLITE_TYPE.TEXT, "交易日期");
        Table_Item Moneyflow_Hsgt_ggt_ss_out = new Table_Item("ggt_ss", SQLITE_TYPE.REAL, "港股通(上海)");
        Table_Item Moneyflow_Hsgt_ggt_sz_out = new Table_Item("ggt_sz", SQLITE_TYPE.REAL, "港股通（深圳）");
        Table_Item Moneyflow_Hsgt_hgt_out = new Table_Item("hgt", SQLITE_TYPE.REAL, "沪股通（百万元）");
        Table_Item Moneyflow_Hsgt_sgt_out = new Table_Item("sgt", SQLITE_TYPE.REAL, "深股通（百万元）");
        Table_Item Moneyflow_Hsgt_north_money_out = new Table_Item("north_money", SQLITE_TYPE.REAL, "北向资金（百万元）");
        Table_Item Moneyflow_Hsgt_south_money_out = new Table_Item("south_money", SQLITE_TYPE.REAL, "南向资金（百万元）\n");


        Moneyflow_Hsgt.addTableItem(Moneyflow_Hsgt_trade_date_out);
        Moneyflow_Hsgt.addTableItem(Moneyflow_Hsgt_ggt_ss_out);
        Moneyflow_Hsgt.addTableItem(Moneyflow_Hsgt_ggt_sz_out);
        Moneyflow_Hsgt.addTableItem(Moneyflow_Hsgt_hgt_out);
        Moneyflow_Hsgt.addTableItem(Moneyflow_Hsgt_sgt_out);
        Moneyflow_Hsgt.addTableItem(Moneyflow_Hsgt_north_money_out);
        Moneyflow_Hsgt.addTableItem(Moneyflow_Hsgt_south_money_out);


//        SQLite_Tables.add(Moneyflow_Hsgt);

    }


    static {
        // 【Table_19_hsgt_top10】   hsgt_top10   获取沪股通、深股通每日前十大成交详细数据
        DB_Table Hsgt_Top10 = new DB_Table("hsgt_top10");

        Hsgt_Top10.tableIndex = 19;
        Hsgt_Top10.viceTableIndex = 1;
        Hsgt_Top10.tableDesc = "沪股通、深股通每日前十大成交详细数据";


        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();
//        String dateNameNow = df.format(calendar.getTime());
        String dateNameNow = "20191018";

        Table_Input_Param Hsgt_Top10_ts_code = new Table_Input_Param("ts_code", "str", "");
        Hsgt_Top10_ts_code.desc = "TS代码";

        Table_Input_Param Hsgt_Top10_trade_date = new Table_Input_Param("trade_date", "str", dateNameNow);
        Hsgt_Top10_trade_date.desc = "交易日期";

        Table_Input_Param Hsgt_Top10_start_date = new Table_Input_Param("start_date", "str", "");
        Hsgt_Top10_start_date.desc = "开始日期";
        Table_Input_Param Hsgt_Top10_end_date = new Table_Input_Param("end_date", "str", "");
        Hsgt_Top10_end_date.desc = "结束日期";

        Table_Input_Param Hsgt_Top10_market_type = new Table_Input_Param("market_type", "str", "");
        Hsgt_Top10_market_type.desc = "市场类型（1：沪市 3：深市）";


        Hsgt_Top10.addTableInputParam(Hsgt_Top10_ts_code);
        Hsgt_Top10.addTableInputParam(Hsgt_Top10_trade_date);
        Hsgt_Top10.addTableInputParam(Hsgt_Top10_start_date);
        Hsgt_Top10.addTableInputParam(Hsgt_Top10_end_date);
        Hsgt_Top10.addTableInputParam(Hsgt_Top10_market_type);


        Table_Item Hsgt_Top10_trade_date_out = new Table_Item("trade_date", SQLITE_TYPE.TEXT, "交易日期");
        Table_Item Hsgt_Top10_ts_code_out = new Table_Item("ts_code", SQLITE_TYPE.TEXT, "股票代码");
        Table_Item Hsgt_Top10_name_out = new Table_Item("name	", SQLITE_TYPE.TEXT, "股票名称");
        Table_Item Hsgt_Top10_close_out = new Table_Item("close", SQLITE_TYPE.REAL, "收盘价");
        Table_Item Hsgt_Top10_change_out = new Table_Item("change", SQLITE_TYPE.REAL, "涨跌额");
        Table_Item Hsgt_Top10_rank_out = new Table_Item("rank	", SQLITE_TYPE.INT, "资金排名");
        Table_Item Hsgt_Top10_market_type_out = new Table_Item("market_type", SQLITE_TYPE.TEXT, "市场类型（1：沪市 3：深市）");
        Table_Item Hsgt_Top10_amount_out = new Table_Item("amount", SQLITE_TYPE.REAL, "成交金额（元）");
        Table_Item Hsgt_Top10_net_amount_out = new Table_Item("net_amount", SQLITE_TYPE.REAL, "净成交金额（元）");
        Table_Item Hsgt_Top10_buy_out = new Table_Item("buy", SQLITE_TYPE.REAL, "买入金额（元）");
        Table_Item Hsgt_Top10_sell_out = new Table_Item("sell	", SQLITE_TYPE.REAL, "卖出金额（元）");


        Hsgt_Top10.addTableItem(Hsgt_Top10_trade_date_out);
        Hsgt_Top10.addTableItem(Hsgt_Top10_ts_code_out);
        Hsgt_Top10.addTableItem(Hsgt_Top10_name_out);
        Hsgt_Top10.addTableItem(Hsgt_Top10_close_out);
        Hsgt_Top10.addTableItem(Hsgt_Top10_change_out);
        Hsgt_Top10.addTableItem(Hsgt_Top10_rank_out);
        Hsgt_Top10.addTableItem(Hsgt_Top10_market_type_out);
        Hsgt_Top10.addTableItem(Hsgt_Top10_amount_out);
        Hsgt_Top10.addTableItem(Hsgt_Top10_net_amount_out);
        Hsgt_Top10.addTableItem(Hsgt_Top10_buy_out);
        Hsgt_Top10.addTableItem(Hsgt_Top10_sell_out);

        SQLite_Tables.add(Hsgt_Top10);


    }

    static {
        //   // 【Table_20_hk_hold】    hk_hold  沪深港股通持股明细
        DB_Table Hk_Hold = new DB_Table("hk_hold");
        Hk_Hold.tableIndex = 20;
        Hk_Hold.viceTableIndex = 1;

        Table_Input_Param Hk_Hold_code = new Table_Input_Param("code", "str", "");
        Table_Input_Param Hk_Hold_ts_code = new Table_Input_Param("ts_code", "str", "");
        Table_Input_Param Hk_Hold_trade_date = new Table_Input_Param("trade_date", "str", "");
        Table_Input_Param Hk_Hold_start_date = new Table_Input_Param("start_date", "str", "");
        Table_Input_Param Hk_Hold_end_date = new Table_Input_Param("end_date", "str", "");
        Table_Input_Param Hk_Hold_exchange = new Table_Input_Param("exchange", "str", "");

        Hk_Hold.addTableInputParam(Hk_Hold_code);
        Hk_Hold.addTableInputParam(Hk_Hold_ts_code);
        Hk_Hold.addTableInputParam(Hk_Hold_trade_date);
        Hk_Hold.addTableInputParam(Hk_Hold_start_date);
        Hk_Hold.addTableInputParam(Hk_Hold_end_date);
        Hk_Hold.addTableInputParam(Hk_Hold_exchange);


        Table_Item Hk_Hold_code_out = new Table_Item("code", SQLITE_TYPE.TEXT, "原始代码");
        Table_Item Hk_Hold_out_trade_date = new Table_Item("trade_date", SQLITE_TYPE.TEXT, "交易日期");
        Table_Item Hk_Hold_out_ts_code = new Table_Item("ts_code", SQLITE_TYPE.TEXT, "TS代码");
        Table_Item Hk_Hold_out_name = new Table_Item("name", SQLITE_TYPE.TEXT, "股票名称");
        Table_Item Hk_Hold_out_vol = new Table_Item("vol", SQLITE_TYPE.INT, "持股数量(股)");
        Table_Item Hk_Hold_out_ratio = new Table_Item("ratio	", SQLITE_TYPE.REAL, "持股占比（%）");
        Table_Item Hk_Hold__out_exchange = new Table_Item("exchange", SQLITE_TYPE.TEXT, "类型：SH沪股通SZ深港通HK港股通");


        Hk_Hold.addTableItem(Hk_Hold_code_out);
        Hk_Hold.addTableItem(Hk_Hold_out_trade_date);
        Hk_Hold.addTableItem(Hk_Hold_out_ts_code);
        Hk_Hold.addTableItem(Hk_Hold_out_name);
        Hk_Hold.addTableItem(Hk_Hold_out_vol);
        Hk_Hold.addTableItem(Hk_Hold_out_ratio);
        Hk_Hold.addTableItem(Hk_Hold__out_exchange);


//        SQLite_Tables.add(Hk_Hold);


    }


    static {
//  【 table_21_ggt_daily 】 获取港股通每日成交信息，数据从2014年开始
        DB_Table Ggt_Daily = new DB_Table("ggt_daily");
        Ggt_Daily.tableIndex = 21;
        Ggt_Daily.viceTableIndex = 1;
        Ggt_Daily.scoreLimit = 2000;
        Ggt_Daily.tableDesc = "获取港股通每日成交信息，数据从2014年开始";
        Table_Input_Param Ggt_Daily_trade_date = new Table_Input_Param("trade_date", "str", "");
        Ggt_Daily_trade_date.desc = "交易日期格式下同支持单日和多日输入";
        Table_Input_Param Ggt_Daily_start_date = new Table_Input_Param("start_date", "str", "");
        Ggt_Daily_start_date.desc = "开始日期";
        Table_Input_Param Ggt_Daily_end_date = new Table_Input_Param("end_date", "str", "");
        Ggt_Daily_end_date.desc = "结束日期";
        Ggt_Daily.addTableInputParam(Ggt_Daily_trade_date);
        Ggt_Daily.addTableInputParam(Ggt_Daily_start_date);
        Ggt_Daily.addTableInputParam(Ggt_Daily_end_date);

        Table_Item Ggt_Daily_trade_date_out = new Table_Item("trade_date", SQLITE_TYPE.TEXT, "交易日期");
        Table_Item Ggt_Daily_buy_amount_out = new Table_Item("buy_amount", SQLITE_TYPE.REAL, "买入成交金额亿元");
        Table_Item Ggt_Daily_buy_volume_out = new Table_Item("buy_volume", SQLITE_TYPE.REAL, "买入成交笔数万笔");
        Table_Item Ggt_Daily_sell_amount_out = new Table_Item("sell_amount", SQLITE_TYPE.REAL, "卖出成交金额亿元");
        Table_Item Ggt_Daily_sell_volume_out = new Table_Item("sell_volume", SQLITE_TYPE.REAL, "卖出成交笔数万笔");
        Ggt_Daily.addTableItem(Ggt_Daily_trade_date_out);
        Ggt_Daily.addTableItem(Ggt_Daily_buy_amount_out);
        Ggt_Daily.addTableItem(Ggt_Daily_buy_volume_out);
        Ggt_Daily.addTableItem(Ggt_Daily_sell_amount_out);
        Ggt_Daily.addTableItem(Ggt_Daily_sell_volume_out);

        SQLite_Tables.add(Ggt_Daily);
    }


    static {
//  【 table_22_ggt_monthly 】 港股通每月成交信息，数据从2014年开始  // 积分5000
        DB_Table Ggt_Monthly = new DB_Table("ggt_monthly");
        Ggt_Monthly.tableIndex = 22;
        Ggt_Monthly.viceTableIndex = 1;
        Ggt_Monthly.scoreLimit = 5000;
        Ggt_Monthly.tableDesc = "港股通每月成交信息，数据从2014年开始";
        Table_Input_Param Ggt_Monthly_month = new Table_Input_Param("month", "str", "");
        Ggt_Monthly_month.desc = "月度格式下同支持多个输入";
        Table_Input_Param Ggt_Monthly_start_month = new Table_Input_Param("start_month", "str", "");
        Ggt_Monthly_start_month.desc = "开始月度";
        Table_Input_Param Ggt_Monthly_end_month = new Table_Input_Param("end_month", "str", "");
        Ggt_Monthly_end_month.desc = "结束月度";
        Ggt_Monthly.addTableInputParam(Ggt_Monthly_month);
        Ggt_Monthly.addTableInputParam(Ggt_Monthly_start_month);
        Ggt_Monthly.addTableInputParam(Ggt_Monthly_end_month);

        Table_Item Ggt_Monthly_month_out = new Table_Item("month", SQLITE_TYPE.TEXT, "交易日期");
        Table_Item Ggt_Monthly_day_buy_amt_out = new Table_Item("day_buy_amt", SQLITE_TYPE.REAL, "当月日均买入成交金额亿元");
        Table_Item Ggt_Monthly_day_buy_vol_out = new Table_Item("day_buy_vol", SQLITE_TYPE.REAL, "当月日均买入成交笔数万笔");
        Table_Item Ggt_Monthly_day_sell_amt_out = new Table_Item("day_sell_amt", SQLITE_TYPE.REAL, "当月日均卖出成交金额亿元");
        Table_Item Ggt_Monthly_day_sell_vol_out = new Table_Item("day_sell_vol", SQLITE_TYPE.REAL, "当月日均卖出成交笔数万笔");
        Table_Item Ggt_Monthly_total_buy_amt_out = new Table_Item("total_buy_amt", SQLITE_TYPE.REAL, "总买入成交金额亿元");
        Table_Item Ggt_Monthly_total_buy_vol_out = new Table_Item("total_buy_vol", SQLITE_TYPE.REAL, "总买入成交笔数万笔");
        Table_Item Ggt_Monthly_total_sell_amt_out = new Table_Item("total_sell_amt", SQLITE_TYPE.REAL, "总卖出成交金额亿元");
        Table_Item Ggt_Monthly_total_sell_vol_out = new Table_Item("total_sell_vol", SQLITE_TYPE.REAL, "总卖出成交笔数万笔");
        Ggt_Monthly.addTableItem(Ggt_Monthly_month_out);
        Ggt_Monthly.addTableItem(Ggt_Monthly_day_buy_amt_out);
        Ggt_Monthly.addTableItem(Ggt_Monthly_day_buy_vol_out);
        Ggt_Monthly.addTableItem(Ggt_Monthly_day_sell_amt_out);
        Ggt_Monthly.addTableItem(Ggt_Monthly_day_sell_vol_out);
        Ggt_Monthly.addTableItem(Ggt_Monthly_total_buy_amt_out);
        Ggt_Monthly.addTableItem(Ggt_Monthly_total_buy_vol_out);
        Ggt_Monthly.addTableItem(Ggt_Monthly_total_sell_amt_out);
        Ggt_Monthly.addTableItem(Ggt_Monthly_total_sell_vol_out);

        SQLite_Tables.add(Ggt_Monthly);
    }


    static {
//  【 table_23_income 】 获取上市公司财务利润表数据

//        积分：用户需要至少500积分才可以调取，具体请参阅积分获取办法
//        提示：当前接口只能按单只股票获取其历史数据，如果需要获取某一季度全部上市公司数据，请使用income_vip接口（参数一致），需积攒5000积分。
        DB_Table Income = new DB_Table("income");
        Income.tableIndex = 23;
        Income.viceTableIndex = 1;
        Income.scoreLimit = 500;
        Income.tableDesc = "获取上市公司财务利润表数据";
        Table_Input_Param Income_ts_code = new Table_Input_Param("ts_code", "str", "");
        Income_ts_code.desc = "股票代码";
        Table_Input_Param Income_ann_date = new Table_Input_Param("ann_date", "str", "");
        Income_ann_date.desc = "公告日期";
        Table_Input_Param Income_start_date = new Table_Input_Param("start_date", "str", "");
        Income_start_date.desc = "公告开始日期";
        Table_Input_Param Income_end_date = new Table_Input_Param("end_date", "str", "");
        Income_end_date.desc = "公告结束日期";
        Table_Input_Param Income_period = new Table_Input_Param("period", "str", "");
        Income_period.desc = "报告期每个季度最后一天的日期比如表示年报";
        Table_Input_Param Income_report_type = new Table_Input_Param("report_type", "str", "");
        Income_report_type.desc = "报告类型参考下表说明";
        Table_Input_Param Income_comp_type = new Table_Input_Param("comp_type", "str", "");
        Income_comp_type.desc = "公司类型一般工商业银行保险证券";
        Income.addTableInputParam(Income_ts_code);
        Income.addTableInputParam(Income_ann_date);
        Income.addTableInputParam(Income_start_date);
        Income.addTableInputParam(Income_end_date);
        Income.addTableInputParam(Income_period);
        Income.addTableInputParam(Income_report_type);
        Income.addTableInputParam(Income_comp_type);

        Table_Item Income_ts_code_out = new Table_Item("ts_code", SQLITE_TYPE.TEXT, "代码");
        Table_Item Income_ann_date_out = new Table_Item("ann_date", SQLITE_TYPE.TEXT, "公告日期");
        Table_Item Income_f_ann_date_out = new Table_Item("f_ann_date", SQLITE_TYPE.TEXT, "实际公告日期");
        Table_Item Income_end_date_out = new Table_Item("end_date", SQLITE_TYPE.TEXT, "报告期");
        Table_Item Income_report_type_out = new Table_Item("report_type", SQLITE_TYPE.TEXT, "报告类型合并报表单季合并调整单季合并表调整合并报表调整前合并报表母公司报表母公司单季表母公司调整单季表母公司调整表母公司调整前报表调整前合并报表母公司调整前报表");
        Table_Item Income_comp_type_out = new Table_Item("comp_type", SQLITE_TYPE.TEXT, "公司类型一般工商业银行保险证券");
        Table_Item Income_basic_eps_out = new Table_Item("basic_eps", SQLITE_TYPE.REAL, "基本每股收益");
        Table_Item Income_diluted_eps_out = new Table_Item("diluted_eps", SQLITE_TYPE.REAL, "稀释每股收益");
        Table_Item Income_total_revenue_out = new Table_Item("total_revenue", SQLITE_TYPE.REAL, "营业总收入");
        Table_Item Income_revenue_out = new Table_Item("revenue", SQLITE_TYPE.REAL, "营业收入");
        Table_Item Income_int_income_out = new Table_Item("int_income", SQLITE_TYPE.REAL, "利息收入");
        Table_Item Income_prem_earned_out = new Table_Item("prem_earned", SQLITE_TYPE.REAL, "已赚保费");
        Table_Item Income_comm_income_out = new Table_Item("comm_income", SQLITE_TYPE.REAL, "手续费及佣金收入");
        Table_Item Income_n_commis_income_out = new Table_Item("n_commis_income", SQLITE_TYPE.REAL, "手续费及佣金净收入");
        Table_Item Income_n_oth_income_out = new Table_Item("n_oth_income", SQLITE_TYPE.REAL, "其他经营净收益");
        Table_Item Income_n_oth_b_income_out = new Table_Item("n_oth_b_income", SQLITE_TYPE.REAL, "加其他业务净收益");
        Table_Item Income_prem_income_out = new Table_Item("prem_income", SQLITE_TYPE.REAL, "保险业务收入");
        Table_Item Income_out_prem_out = new Table_Item("out_prem", SQLITE_TYPE.REAL, "减分出保费");
        Table_Item Income_une_prem_reser_out = new Table_Item("une_prem_reser", SQLITE_TYPE.REAL, "提取未到期责任准备金");
        Table_Item Income_reins_income_out = new Table_Item("reins_income", SQLITE_TYPE.REAL, "其中分保费收入");
        Table_Item Income_n_sec_tb_income_out = new Table_Item("n_sec_tb_income", SQLITE_TYPE.REAL, "代理买卖证券业务净收入");
        Table_Item Income_n_sec_uw_income_out = new Table_Item("n_sec_uw_income", SQLITE_TYPE.REAL, "证券承销业务净收入");
        Table_Item Income_n_asset_mg_income_out = new Table_Item("n_asset_mg_income", SQLITE_TYPE.REAL, "受托客户资产管理业务净收入");
        Table_Item Income_oth_b_income_out = new Table_Item("oth_b_income", SQLITE_TYPE.REAL, "其他业务收入");
        Table_Item Income_fv_value_chg_gain_out = new Table_Item("fv_value_chg_gain", SQLITE_TYPE.REAL, "加公允价值变动净收益");
        Table_Item Income_invest_income_out = new Table_Item("invest_income", SQLITE_TYPE.REAL, "加投资净收益");
        Table_Item Income_ass_invest_income_out = new Table_Item("ass_invest_income", SQLITE_TYPE.REAL, "其中对联营企业和合营企业的投资收益");
        Table_Item Income_forex_gain_out = new Table_Item("forex_gain", SQLITE_TYPE.REAL, "加汇兑净收益");
        Table_Item Income_total_cogs_out = new Table_Item("total_cogs", SQLITE_TYPE.REAL, "营业总成本");
        Table_Item Income_oper_cost_out = new Table_Item("oper_cost", SQLITE_TYPE.REAL, "减营业成本");
        Table_Item Income_int_exp_out = new Table_Item("int_exp", SQLITE_TYPE.REAL, "减利息支出");
        Table_Item Income_comm_exp_out = new Table_Item("comm_exp", SQLITE_TYPE.REAL, "减手续费及佣金支出");
        Table_Item Income_biz_tax_surchg_out = new Table_Item("biz_tax_surchg", SQLITE_TYPE.REAL, "减营业税金及附加");
        Table_Item Income_sell_exp_out = new Table_Item("sell_exp", SQLITE_TYPE.REAL, "减销售费用");
        Table_Item Income_admin_exp_out = new Table_Item("admin_exp", SQLITE_TYPE.REAL, "减管理费用");
        Table_Item Income_fin_exp_out = new Table_Item("fin_exp", SQLITE_TYPE.REAL, "减财务费用");
        Table_Item Income_assets_impair_loss_out = new Table_Item("assets_impair_loss", SQLITE_TYPE.REAL, "减资产减值损失");
        Table_Item Income_prem_refund_out = new Table_Item("prem_refund", SQLITE_TYPE.REAL, "退保金");
        Table_Item Income_compens_payout_out = new Table_Item("compens_payout", SQLITE_TYPE.REAL, "赔付总支出");
        Table_Item Income_reser_insur_liab_out = new Table_Item("reser_insur_liab", SQLITE_TYPE.REAL, "提取保险责任准备金");
        Table_Item Income_div_payt_out = new Table_Item("div_payt", SQLITE_TYPE.REAL, "保户红利支出");
        Table_Item Income_reins_exp_out = new Table_Item("reins_exp", SQLITE_TYPE.REAL, "分保费用");
        Table_Item Income_oper_exp_out = new Table_Item("oper_exp", SQLITE_TYPE.REAL, "营业支出");
        Table_Item Income_compens_payout_refu_out = new Table_Item("compens_payout_refu", SQLITE_TYPE.REAL, "减摊回赔付支出");
        Table_Item Income_insur_reser_refu_out = new Table_Item("insur_reser_refu", SQLITE_TYPE.REAL, "减摊回保险责任准备金");
        Table_Item Income_reins_cost_refund_out = new Table_Item("reins_cost_refund", SQLITE_TYPE.REAL, "减摊回分保费用");
        Table_Item Income_other_bus_cost_out = new Table_Item("other_bus_cost", SQLITE_TYPE.REAL, "其他业务成本");
        Table_Item Income_operate_profit_out = new Table_Item("operate_profit", SQLITE_TYPE.REAL, "营业利润");
        Table_Item Income_non_oper_income_out = new Table_Item("non_oper_income", SQLITE_TYPE.REAL, "加营业外收入");
        Table_Item Income_non_oper_exp_out = new Table_Item("non_oper_exp", SQLITE_TYPE.REAL, "减营业外支出");
        Table_Item Income_nca_disploss_out = new Table_Item("nca_disploss", SQLITE_TYPE.REAL, "其中减非流动资产处置净损失");
        Table_Item Income_total_profit_out = new Table_Item("total_profit", SQLITE_TYPE.REAL, "利润总额");
        Table_Item Income_income_tax_out = new Table_Item("income_tax", SQLITE_TYPE.REAL, "所得税费用");
        Table_Item Income_n_income_out = new Table_Item("n_income", SQLITE_TYPE.REAL, "净利润含少数股东损益");
        Table_Item Income_n_income_attr_p_out = new Table_Item("n_income_attr_p", SQLITE_TYPE.REAL, "净利润不含少数股东损益");
        Table_Item Income_minority_gain_out = new Table_Item("minority_gain", SQLITE_TYPE.REAL, "少数股东损益");
        Table_Item Income_oth_compr_income_out = new Table_Item("oth_compr_income", SQLITE_TYPE.REAL, "其他综合收益");
        Table_Item Income_t_compr_income_out = new Table_Item("t_compr_income", SQLITE_TYPE.REAL, "综合收益总额");
        Table_Item Income_compr_inc_attr_p_out = new Table_Item("compr_inc_attr_p", SQLITE_TYPE.REAL, "归属于母公司或股东的综合收益总额");
        Table_Item Income_compr_inc_attr_m_s_out = new Table_Item("compr_inc_attr_m_s", SQLITE_TYPE.REAL, "归属于少数股东的综合收益总额");
        Table_Item Income_ebit_out = new Table_Item("ebit", SQLITE_TYPE.REAL, "息税前利润");
        Table_Item Income_ebitda_out = new Table_Item("ebitda", SQLITE_TYPE.REAL, "息税折旧摊销前利润");
        Table_Item Income_insurance_exp_out = new Table_Item("insurance_exp", SQLITE_TYPE.REAL, "保险业务支出");
        Table_Item Income_undist_profit_out = new Table_Item("undist_profit", SQLITE_TYPE.REAL, "年初未分配利润");
        Table_Item Income_distable_profit_out = new Table_Item("distable_profit", SQLITE_TYPE.REAL, "可分配利润");
        Table_Item Income_update_flag_out = new Table_Item("update_flag", SQLITE_TYPE.TEXT, "更新标识未修改更正过");
        Income.addTableItem(Income_ts_code_out);
        Income.addTableItem(Income_ann_date_out);
        Income.addTableItem(Income_f_ann_date_out);
        Income.addTableItem(Income_end_date_out);
        Income.addTableItem(Income_report_type_out);
        Income.addTableItem(Income_comp_type_out);
        Income.addTableItem(Income_basic_eps_out);
        Income.addTableItem(Income_diluted_eps_out);
        Income.addTableItem(Income_total_revenue_out);
        Income.addTableItem(Income_revenue_out);
        Income.addTableItem(Income_int_income_out);
        Income.addTableItem(Income_prem_earned_out);
        Income.addTableItem(Income_comm_income_out);
        Income.addTableItem(Income_n_commis_income_out);
        Income.addTableItem(Income_n_oth_income_out);
        Income.addTableItem(Income_n_oth_b_income_out);
        Income.addTableItem(Income_prem_income_out);
        Income.addTableItem(Income_out_prem_out);
        Income.addTableItem(Income_une_prem_reser_out);
        Income.addTableItem(Income_reins_income_out);
        Income.addTableItem(Income_n_sec_tb_income_out);
        Income.addTableItem(Income_n_sec_uw_income_out);
        Income.addTableItem(Income_n_asset_mg_income_out);
        Income.addTableItem(Income_oth_b_income_out);
        Income.addTableItem(Income_fv_value_chg_gain_out);
        Income.addTableItem(Income_invest_income_out);
        Income.addTableItem(Income_ass_invest_income_out);
        Income.addTableItem(Income_forex_gain_out);
        Income.addTableItem(Income_total_cogs_out);
        Income.addTableItem(Income_oper_cost_out);
        Income.addTableItem(Income_int_exp_out);
        Income.addTableItem(Income_comm_exp_out);
        Income.addTableItem(Income_biz_tax_surchg_out);
        Income.addTableItem(Income_sell_exp_out);
        Income.addTableItem(Income_admin_exp_out);
        Income.addTableItem(Income_fin_exp_out);
        Income.addTableItem(Income_assets_impair_loss_out);
        Income.addTableItem(Income_prem_refund_out);
        Income.addTableItem(Income_compens_payout_out);
        Income.addTableItem(Income_reser_insur_liab_out);
        Income.addTableItem(Income_div_payt_out);
        Income.addTableItem(Income_reins_exp_out);
        Income.addTableItem(Income_oper_exp_out);
        Income.addTableItem(Income_compens_payout_refu_out);
        Income.addTableItem(Income_insur_reser_refu_out);
        Income.addTableItem(Income_reins_cost_refund_out);
        Income.addTableItem(Income_other_bus_cost_out);
        Income.addTableItem(Income_operate_profit_out);
        Income.addTableItem(Income_non_oper_income_out);
        Income.addTableItem(Income_non_oper_exp_out);
        Income.addTableItem(Income_nca_disploss_out);
        Income.addTableItem(Income_total_profit_out);
        Income.addTableItem(Income_income_tax_out);
        Income.addTableItem(Income_n_income_out);
        Income.addTableItem(Income_n_income_attr_p_out);
        Income.addTableItem(Income_minority_gain_out);
        Income.addTableItem(Income_oth_compr_income_out);
        Income.addTableItem(Income_t_compr_income_out);
        Income.addTableItem(Income_compr_inc_attr_p_out);
        Income.addTableItem(Income_compr_inc_attr_m_s_out);
        Income.addTableItem(Income_ebit_out);
        Income.addTableItem(Income_ebitda_out);
        Income.addTableItem(Income_insurance_exp_out);
        Income.addTableItem(Income_undist_profit_out);
        Income.addTableItem(Income_distable_profit_out);
        Income.addTableItem(Income_update_flag_out);

        SQLite_Tables.add(Income);
    }


    static {
//  【 table_24_balancesheet 】 获取上市公司资产负债表
        DB_Table Balancesheet = new DB_Table("balancesheet");
        Balancesheet.tableIndex = 24;
        Balancesheet.viceTableIndex = 1;
        Balancesheet.scoreLimit = 500;
        Balancesheet.tableDesc = "获取上市公司资产负债表";
        Table_Input_Param Balancesheet_ts_code = new Table_Input_Param("ts_code", "str", "");
        Balancesheet_ts_code.desc = "股票代码";
        Table_Input_Param Balancesheet_ann_date = new Table_Input_Param("ann_date", "str", "");
        Balancesheet_ann_date.desc = "公告日期";
        Table_Input_Param Balancesheet_start_date = new Table_Input_Param("start_date", "str", "");
        Balancesheet_start_date.desc = "公告开始日期";
        Table_Input_Param Balancesheet_end_date = new Table_Input_Param("end_date", "str", "");
        Balancesheet_end_date.desc = "公告结束日期";
        Table_Input_Param Balancesheet_period = new Table_Input_Param("period", "str", "");
        Balancesheet_period.desc = "报告期每个季度最后一天的日期比如表示年报";
        Table_Input_Param Balancesheet_report_type = new Table_Input_Param("report_type", "str", "");
        Balancesheet_report_type.desc = "报告类型见下方详细说明";
        Table_Input_Param Balancesheet_comp_type = new Table_Input_Param("comp_type", "str", "");
        Balancesheet_comp_type.desc = "公司类型一般工商业银行保险证券";
        Balancesheet.addTableInputParam(Balancesheet_ts_code);
        Balancesheet.addTableInputParam(Balancesheet_ann_date);
        Balancesheet.addTableInputParam(Balancesheet_start_date);
        Balancesheet.addTableInputParam(Balancesheet_end_date);
        Balancesheet.addTableInputParam(Balancesheet_period);
        Balancesheet.addTableInputParam(Balancesheet_report_type);
        Balancesheet.addTableInputParam(Balancesheet_comp_type);

        Table_Item Balancesheet_ts_code_out = new Table_Item("ts_code", SQLITE_TYPE.TEXT, "股票代码");
        Table_Item Balancesheet_ann_date_out = new Table_Item("ann_date", SQLITE_TYPE.TEXT, "公告日期");
        Table_Item Balancesheet_f_ann_date_out = new Table_Item("f_ann_date", SQLITE_TYPE.TEXT, "实际公告日期");
        Table_Item Balancesheet_end_date_out = new Table_Item("end_date", SQLITE_TYPE.TEXT, "报告期");
        Table_Item Balancesheet_report_type_out = new Table_Item("report_type", SQLITE_TYPE.TEXT, "报表类型");
        Table_Item Balancesheet_comp_type_out = new Table_Item("comp_type", SQLITE_TYPE.TEXT, "公司类型");
        Table_Item Balancesheet_total_share_out = new Table_Item("total_share", SQLITE_TYPE.REAL, "期末总股本");
        Table_Item Balancesheet_cap_rese_out = new Table_Item("cap_rese", SQLITE_TYPE.REAL, "资本公积金");
        Table_Item Balancesheet_undistr_porfit_out = new Table_Item("undistr_porfit", SQLITE_TYPE.REAL, "未分配利润");
        Table_Item Balancesheet_surplus_rese_out = new Table_Item("surplus_rese", SQLITE_TYPE.REAL, "盈余公积金");
        Table_Item Balancesheet_special_rese_out = new Table_Item("special_rese", SQLITE_TYPE.REAL, "专项储备");
        Table_Item Balancesheet_money_cap_out = new Table_Item("money_cap", SQLITE_TYPE.REAL, "货币资金");
        Table_Item Balancesheet_trad_asset_out = new Table_Item("trad_asset", SQLITE_TYPE.REAL, "交易性金融资产");
        Table_Item Balancesheet_notes_receiv_out = new Table_Item("notes_receiv", SQLITE_TYPE.REAL, "应收票据");
        Table_Item Balancesheet_accounts_receiv_out = new Table_Item("accounts_receiv", SQLITE_TYPE.REAL, "应收账款");
        Table_Item Balancesheet_oth_receiv_out = new Table_Item("oth_receiv", SQLITE_TYPE.REAL, "其他应收款");
        Table_Item Balancesheet_prepayment_out = new Table_Item("prepayment", SQLITE_TYPE.REAL, "预付款项");
        Table_Item Balancesheet_div_receiv_out = new Table_Item("div_receiv", SQLITE_TYPE.REAL, "应收股利");
        Table_Item Balancesheet_int_receiv_out = new Table_Item("int_receiv", SQLITE_TYPE.REAL, "应收利息");
        Table_Item Balancesheet_inventories_out = new Table_Item("inventories", SQLITE_TYPE.REAL, "存货");
        Table_Item Balancesheet_amor_exp_out = new Table_Item("amor_exp", SQLITE_TYPE.REAL, "长期待摊费用");
        Table_Item Balancesheet_nca_within_1y_out = new Table_Item("nca_within_1y", SQLITE_TYPE.REAL, "一年内到期的非流动资产");
        Table_Item Balancesheet_sett_rsrv_out = new Table_Item("sett_rsrv", SQLITE_TYPE.REAL, "结算备付金");
        Table_Item Balancesheet_loanto_oth_bank_fi_out = new Table_Item("loanto_oth_bank_fi", SQLITE_TYPE.REAL, "拆出资金");
        Table_Item Balancesheet_premium_receiv_out = new Table_Item("premium_receiv", SQLITE_TYPE.REAL, "应收保费");
        Table_Item Balancesheet_reinsur_receiv_out = new Table_Item("reinsur_receiv", SQLITE_TYPE.REAL, "应收分保账款");
        Table_Item Balancesheet_reinsur_res_receiv_out = new Table_Item("reinsur_res_receiv", SQLITE_TYPE.REAL, "应收分保合同准备金");
        Table_Item Balancesheet_pur_resale_fa_out = new Table_Item("pur_resale_fa", SQLITE_TYPE.REAL, "买入返售金融资产");
        Table_Item Balancesheet_oth_cur_assets_out = new Table_Item("oth_cur_assets", SQLITE_TYPE.REAL, "其他流动资产");
        Table_Item Balancesheet_total_cur_assets_out = new Table_Item("total_cur_assets", SQLITE_TYPE.REAL, "流动资产合计");
        Table_Item Balancesheet_fa_avail_for_sale_out = new Table_Item("fa_avail_for_sale", SQLITE_TYPE.REAL, "可供出售金融资产");
        Table_Item Balancesheet_htm_invest_out = new Table_Item("htm_invest", SQLITE_TYPE.REAL, "持有至到期投资");
        Table_Item Balancesheet_lt_eqt_invest_out = new Table_Item("lt_eqt_invest", SQLITE_TYPE.REAL, "长期股权投资");
        Table_Item Balancesheet_invest_real_estate_out = new Table_Item("invest_real_estate", SQLITE_TYPE.REAL, "投资性房地产");
        Table_Item Balancesheet_time_deposits_out = new Table_Item("time_deposits", SQLITE_TYPE.REAL, "定期存款");
        Table_Item Balancesheet_oth_assets_out = new Table_Item("oth_assets", SQLITE_TYPE.REAL, "其他资产");
        Table_Item Balancesheet_lt_rec_out = new Table_Item("lt_rec", SQLITE_TYPE.REAL, "长期应收款");
        Table_Item Balancesheet_fix_assets_out = new Table_Item("fix_assets", SQLITE_TYPE.REAL, "固定资产");
        Table_Item Balancesheet_cip_out = new Table_Item("cip", SQLITE_TYPE.REAL, "在建工程");
        Table_Item Balancesheet_const_materials_out = new Table_Item("const_materials", SQLITE_TYPE.REAL, "工程物资");
        Table_Item Balancesheet_fixed_assets_disp_out = new Table_Item("fixed_assets_disp", SQLITE_TYPE.REAL, "固定资产清理");
        Table_Item Balancesheet_produc_bio_assets_out = new Table_Item("produc_bio_assets", SQLITE_TYPE.REAL, "生产性生物资产");
        Table_Item Balancesheet_oil_and_gas_assets_out = new Table_Item("oil_and_gas_assets", SQLITE_TYPE.REAL, "油气资产");
        Table_Item Balancesheet_intan_assets_out = new Table_Item("intan_assets", SQLITE_TYPE.REAL, "无形资产");
        Table_Item Balancesheet_r_and_d_out = new Table_Item("r_and_d", SQLITE_TYPE.REAL, "研发支出");
        Table_Item Balancesheet_goodwill_out = new Table_Item("goodwill", SQLITE_TYPE.REAL, "商誉");
        Table_Item Balancesheet_lt_amor_exp_out = new Table_Item("lt_amor_exp", SQLITE_TYPE.REAL, "长期待摊费用");
        Table_Item Balancesheet_defer_tax_assets_out = new Table_Item("defer_tax_assets", SQLITE_TYPE.REAL, "递延所得税资产");
        Table_Item Balancesheet_decr_in_disbur_out = new Table_Item("decr_in_disbur", SQLITE_TYPE.REAL, "发放贷款及垫款");
        Table_Item Balancesheet_oth_nca_out = new Table_Item("oth_nca", SQLITE_TYPE.REAL, "其他非流动资产");
        Table_Item Balancesheet_total_nca_out = new Table_Item("total_nca", SQLITE_TYPE.REAL, "非流动资产合计");
        Table_Item Balancesheet_cash_reser_cb_out = new Table_Item("cash_reser_cb", SQLITE_TYPE.REAL, "现金及存放中央银行款项");
        Table_Item Balancesheet_depos_in_oth_bfi_out = new Table_Item("depos_in_oth_bfi", SQLITE_TYPE.REAL, "存放同业和其它金融机构款项");
        Table_Item Balancesheet_prec_metals_out = new Table_Item("prec_metals", SQLITE_TYPE.REAL, "贵金属");
        Table_Item Balancesheet_deriv_assets_out = new Table_Item("deriv_assets", SQLITE_TYPE.REAL, "衍生金融资产");
        Table_Item Balancesheet_rr_reins_une_prem_out = new Table_Item("rr_reins_une_prem", SQLITE_TYPE.REAL, "应收分保未到期责任准备金");
        Table_Item Balancesheet_rr_reins_outstd_cla_out = new Table_Item("rr_reins_outstd_cla", SQLITE_TYPE.REAL, "应收分保未决赔款准备金");
        Table_Item Balancesheet_rr_reins_lins_liab_out = new Table_Item("rr_reins_lins_liab", SQLITE_TYPE.REAL, "应收分保寿险责任准备金");
        Table_Item Balancesheet_rr_reins_lthins_liab_out = new Table_Item("rr_reins_lthins_liab", SQLITE_TYPE.REAL, "应收分保长期健康险责任准备金");
        Table_Item Balancesheet_refund_depos_out = new Table_Item("refund_depos", SQLITE_TYPE.REAL, "存出保证金");
        Table_Item Balancesheet_ph_pledge_loans_out = new Table_Item("ph_pledge_loans", SQLITE_TYPE.REAL, "保户质押贷款");
        Table_Item Balancesheet_refund_cap_depos_out = new Table_Item("refund_cap_depos", SQLITE_TYPE.REAL, "存出资本保证金");
        Table_Item Balancesheet_indep_acct_assets_out = new Table_Item("indep_acct_assets", SQLITE_TYPE.REAL, "独立账户资产");
        Table_Item Balancesheet_client_depos_out = new Table_Item("client_depos", SQLITE_TYPE.REAL, "其中客户资金存款");
        Table_Item Balancesheet_client_prov_out = new Table_Item("client_prov", SQLITE_TYPE.REAL, "其中客户备付金");
        Table_Item Balancesheet_transac_seat_fee_out = new Table_Item("transac_seat_fee", SQLITE_TYPE.REAL, "其中交易席位费");
        Table_Item Balancesheet_invest_as_receiv_out = new Table_Item("invest_as_receiv", SQLITE_TYPE.REAL, "应收款项类投资");
        Table_Item Balancesheet_total_assets_out = new Table_Item("total_assets", SQLITE_TYPE.REAL, "资产总计");
        Table_Item Balancesheet_lt_borr_out = new Table_Item("lt_borr", SQLITE_TYPE.REAL, "长期借款");
        Table_Item Balancesheet_st_borr_out = new Table_Item("st_borr", SQLITE_TYPE.REAL, "短期借款");
        Table_Item Balancesheet_cb_borr_out = new Table_Item("cb_borr", SQLITE_TYPE.REAL, "向中央银行借款");
        Table_Item Balancesheet_depos_ib_deposits_out = new Table_Item("depos_ib_deposits", SQLITE_TYPE.REAL, "吸收存款及同业存放");
        Table_Item Balancesheet_loan_oth_bank_out = new Table_Item("loan_oth_bank", SQLITE_TYPE.REAL, "拆入资金");
        Table_Item Balancesheet_trading_fl_out = new Table_Item("trading_fl", SQLITE_TYPE.REAL, "交易性金融负债");
        Table_Item Balancesheet_notes_payable_out = new Table_Item("notes_payable", SQLITE_TYPE.REAL, "应付票据");
        Table_Item Balancesheet_acct_payable_out = new Table_Item("acct_payable", SQLITE_TYPE.REAL, "应付账款");
        Table_Item Balancesheet_adv_receipts_out = new Table_Item("adv_receipts", SQLITE_TYPE.REAL, "预收款项");
        Table_Item Balancesheet_sold_for_repur_fa_out = new Table_Item("sold_for_repur_fa", SQLITE_TYPE.REAL, "卖出回购金融资产款");
        Table_Item Balancesheet_comm_payable_out = new Table_Item("comm_payable", SQLITE_TYPE.REAL, "应付手续费及佣金");
        Table_Item Balancesheet_payroll_payable_out = new Table_Item("payroll_payable", SQLITE_TYPE.REAL, "应付职工薪酬");
        Table_Item Balancesheet_taxes_payable_out = new Table_Item("taxes_payable", SQLITE_TYPE.REAL, "应交税费");
        Table_Item Balancesheet_int_payable_out = new Table_Item("int_payable", SQLITE_TYPE.REAL, "应付利息");
        Table_Item Balancesheet_div_payable_out = new Table_Item("div_payable", SQLITE_TYPE.REAL, "应付股利");
        Table_Item Balancesheet_oth_payable_out = new Table_Item("oth_payable", SQLITE_TYPE.REAL, "其他应付款");
        Table_Item Balancesheet_acc_exp_out = new Table_Item("acc_exp", SQLITE_TYPE.REAL, "预提费用");
        Table_Item Balancesheet_deferred_inc_out = new Table_Item("deferred_inc", SQLITE_TYPE.REAL, "递延收益");
        Table_Item Balancesheet_st_bonds_payable_out = new Table_Item("st_bonds_payable", SQLITE_TYPE.REAL, "应付短期债券");
        Table_Item Balancesheet_payable_to_reinsurer_out = new Table_Item("payable_to_reinsurer", SQLITE_TYPE.REAL, "应付分保账款");
        Table_Item Balancesheet_rsrv_insur_cont_out = new Table_Item("rsrv_insur_cont", SQLITE_TYPE.REAL, "保险合同准备金");
        Table_Item Balancesheet_acting_trading_sec_out = new Table_Item("acting_trading_sec", SQLITE_TYPE.REAL, "代理买卖证券款");
        Table_Item Balancesheet_acting_uw_sec_out = new Table_Item("acting_uw_sec", SQLITE_TYPE.REAL, "代理承销证券款");
        Table_Item Balancesheet_non_cur_liab_due_1y_out = new Table_Item("non_cur_liab_due_1y", SQLITE_TYPE.REAL, "一年内到期的非流动负债");
        Table_Item Balancesheet_oth_cur_liab_out = new Table_Item("oth_cur_liab", SQLITE_TYPE.REAL, "其他流动负债");
        Table_Item Balancesheet_total_cur_liab_out = new Table_Item("total_cur_liab", SQLITE_TYPE.REAL, "流动负债合计");
        Table_Item Balancesheet_bond_payable_out = new Table_Item("bond_payable", SQLITE_TYPE.REAL, "应付债券");
        Table_Item Balancesheet_lt_payable_out = new Table_Item("lt_payable", SQLITE_TYPE.REAL, "长期应付款");
        Table_Item Balancesheet_specific_payables_out = new Table_Item("specific_payables", SQLITE_TYPE.REAL, "专项应付款");
        Table_Item Balancesheet_estimated_liab_out = new Table_Item("estimated_liab", SQLITE_TYPE.REAL, "预计负债");
        Table_Item Balancesheet_defer_tax_liab_out = new Table_Item("defer_tax_liab", SQLITE_TYPE.REAL, "递延所得税负债");
        Table_Item Balancesheet_defer_inc_non_cur_liab_out = new Table_Item("defer_inc_non_cur_liab", SQLITE_TYPE.REAL, "递延收益非流动负债");
        Table_Item Balancesheet_oth_ncl_out = new Table_Item("oth_ncl", SQLITE_TYPE.REAL, "其他非流动负债");
        Table_Item Balancesheet_total_ncl_out = new Table_Item("total_ncl", SQLITE_TYPE.REAL, "非流动负债合计");
        Table_Item Balancesheet_depos_oth_bfi_out = new Table_Item("depos_oth_bfi", SQLITE_TYPE.REAL, "同业和其它金融机构存放款项");
        Table_Item Balancesheet_deriv_liab_out = new Table_Item("deriv_liab", SQLITE_TYPE.REAL, "衍生金融负债");
        Table_Item Balancesheet_depos_out = new Table_Item("depos", SQLITE_TYPE.REAL, "吸收存款");
        Table_Item Balancesheet_agency_bus_liab_out = new Table_Item("agency_bus_liab", SQLITE_TYPE.REAL, "代理业务负债");
        Table_Item Balancesheet_oth_liab_out = new Table_Item("oth_liab", SQLITE_TYPE.REAL, "其他负债");
        Table_Item Balancesheet_prem_receiv_adva_out = new Table_Item("prem_receiv_adva", SQLITE_TYPE.REAL, "预收保费");
        Table_Item Balancesheet_depos_received_out = new Table_Item("depos_received", SQLITE_TYPE.REAL, "存入保证金");
        Table_Item Balancesheet_ph_invest_out = new Table_Item("ph_invest", SQLITE_TYPE.REAL, "保户储金及投资款");
        Table_Item Balancesheet_reser_une_prem_out = new Table_Item("reser_une_prem", SQLITE_TYPE.REAL, "未到期责任准备金");
        Table_Item Balancesheet_reser_outstd_claims_out = new Table_Item("reser_outstd_claims", SQLITE_TYPE.REAL, "未决赔款准备金");
        Table_Item Balancesheet_reser_lins_liab_out = new Table_Item("reser_lins_liab", SQLITE_TYPE.REAL, "寿险责任准备金");
        Table_Item Balancesheet_reser_lthins_liab_out = new Table_Item("reser_lthins_liab", SQLITE_TYPE.REAL, "长期健康险责任准备金");
        Table_Item Balancesheet_indept_acc_liab_out = new Table_Item("indept_acc_liab", SQLITE_TYPE.REAL, "独立账户负债");
        Table_Item Balancesheet_pledge_borr_out = new Table_Item("pledge_borr", SQLITE_TYPE.REAL, "其中质押借款");
        Table_Item Balancesheet_indem_payable_out = new Table_Item("indem_payable", SQLITE_TYPE.REAL, "应付赔付款");
        Table_Item Balancesheet_policy_div_payable_out = new Table_Item("policy_div_payable", SQLITE_TYPE.REAL, "应付保单红利");
        Table_Item Balancesheet_total_liab_out = new Table_Item("total_liab", SQLITE_TYPE.REAL, "负债合计");
        Table_Item Balancesheet_treasury_share_out = new Table_Item("treasury_share", SQLITE_TYPE.REAL, "减库存股");
        Table_Item Balancesheet_ordin_risk_reser_out = new Table_Item("ordin_risk_reser", SQLITE_TYPE.REAL, "一般风险准备");
        Table_Item Balancesheet_forex_differ_out = new Table_Item("forex_differ", SQLITE_TYPE.REAL, "外币报表折算差额");
        Table_Item Balancesheet_invest_loss_unconf_out = new Table_Item("invest_loss_unconf", SQLITE_TYPE.REAL, "未确认的投资损失");
        Table_Item Balancesheet_minority_int_out = new Table_Item("minority_int", SQLITE_TYPE.REAL, "少数股东权益");
        Table_Item Balancesheet_total_hldr_eqy_exc_min_int_out = new Table_Item("total_hldr_eqy_exc_min_int", SQLITE_TYPE.REAL, "股东权益合计不含少数股东权益");
        Table_Item Balancesheet_total_hldr_eqy_inc_min_int_out = new Table_Item("total_hldr_eqy_inc_min_int", SQLITE_TYPE.REAL, "股东权益合计含少数股东权益");
        Table_Item Balancesheet_total_liab_hldr_eqy_out = new Table_Item("total_liab_hldr_eqy", SQLITE_TYPE.REAL, "负债及股东权益总计");
        Table_Item Balancesheet_lt_payroll_payable_out = new Table_Item("lt_payroll_payable", SQLITE_TYPE.REAL, "长期应付职工薪酬");
        Table_Item Balancesheet_oth_comp_income_out = new Table_Item("oth_comp_income", SQLITE_TYPE.REAL, "其他综合收益");
        Table_Item Balancesheet_oth_eqt_tools_out = new Table_Item("oth_eqt_tools", SQLITE_TYPE.REAL, "其他权益工具");
        Table_Item Balancesheet_oth_eqt_tools_p_shr_out = new Table_Item("oth_eqt_tools_p_shr", SQLITE_TYPE.REAL, "其他权益工具优先股");
        Table_Item Balancesheet_lending_funds_out = new Table_Item("lending_funds", SQLITE_TYPE.REAL, "融出资金");
        Table_Item Balancesheet_acc_receivable_out = new Table_Item("acc_receivable", SQLITE_TYPE.REAL, "应收款项");
        Table_Item Balancesheet_st_fin_payable_out = new Table_Item("st_fin_payable", SQLITE_TYPE.REAL, "应付短期融资款");
        Table_Item Balancesheet_payables_out = new Table_Item("payables", SQLITE_TYPE.REAL, "应付款项");
        Table_Item Balancesheet_hfs_assets_out = new Table_Item("hfs_assets", SQLITE_TYPE.REAL, "持有待售的资产");
        Table_Item Balancesheet_hfs_sales_out = new Table_Item("hfs_sales", SQLITE_TYPE.REAL, "持有待售的负债");
        Table_Item Balancesheet_update_flag_out = new Table_Item("update_flag", SQLITE_TYPE.TEXT, "更新标识");
        Balancesheet.addTableItem(Balancesheet_ts_code_out);
        Balancesheet.addTableItem(Balancesheet_ann_date_out);
        Balancesheet.addTableItem(Balancesheet_f_ann_date_out);
        Balancesheet.addTableItem(Balancesheet_end_date_out);
        Balancesheet.addTableItem(Balancesheet_report_type_out);
        Balancesheet.addTableItem(Balancesheet_comp_type_out);
        Balancesheet.addTableItem(Balancesheet_total_share_out);
        Balancesheet.addTableItem(Balancesheet_cap_rese_out);
        Balancesheet.addTableItem(Balancesheet_undistr_porfit_out);
        Balancesheet.addTableItem(Balancesheet_surplus_rese_out);
        Balancesheet.addTableItem(Balancesheet_special_rese_out);
        Balancesheet.addTableItem(Balancesheet_money_cap_out);
        Balancesheet.addTableItem(Balancesheet_trad_asset_out);
        Balancesheet.addTableItem(Balancesheet_notes_receiv_out);
        Balancesheet.addTableItem(Balancesheet_accounts_receiv_out);
        Balancesheet.addTableItem(Balancesheet_oth_receiv_out);
        Balancesheet.addTableItem(Balancesheet_prepayment_out);
        Balancesheet.addTableItem(Balancesheet_div_receiv_out);
        Balancesheet.addTableItem(Balancesheet_int_receiv_out);
        Balancesheet.addTableItem(Balancesheet_inventories_out);
        Balancesheet.addTableItem(Balancesheet_amor_exp_out);
        Balancesheet.addTableItem(Balancesheet_nca_within_1y_out);
        Balancesheet.addTableItem(Balancesheet_sett_rsrv_out);
        Balancesheet.addTableItem(Balancesheet_loanto_oth_bank_fi_out);
        Balancesheet.addTableItem(Balancesheet_premium_receiv_out);
        Balancesheet.addTableItem(Balancesheet_reinsur_receiv_out);
        Balancesheet.addTableItem(Balancesheet_reinsur_res_receiv_out);
        Balancesheet.addTableItem(Balancesheet_pur_resale_fa_out);
        Balancesheet.addTableItem(Balancesheet_oth_cur_assets_out);
        Balancesheet.addTableItem(Balancesheet_total_cur_assets_out);
        Balancesheet.addTableItem(Balancesheet_fa_avail_for_sale_out);
        Balancesheet.addTableItem(Balancesheet_htm_invest_out);
        Balancesheet.addTableItem(Balancesheet_lt_eqt_invest_out);
        Balancesheet.addTableItem(Balancesheet_invest_real_estate_out);
        Balancesheet.addTableItem(Balancesheet_time_deposits_out);
        Balancesheet.addTableItem(Balancesheet_oth_assets_out);
        Balancesheet.addTableItem(Balancesheet_lt_rec_out);
        Balancesheet.addTableItem(Balancesheet_fix_assets_out);
        Balancesheet.addTableItem(Balancesheet_cip_out);
        Balancesheet.addTableItem(Balancesheet_const_materials_out);
        Balancesheet.addTableItem(Balancesheet_fixed_assets_disp_out);
        Balancesheet.addTableItem(Balancesheet_produc_bio_assets_out);
        Balancesheet.addTableItem(Balancesheet_oil_and_gas_assets_out);
        Balancesheet.addTableItem(Balancesheet_intan_assets_out);
        Balancesheet.addTableItem(Balancesheet_r_and_d_out);
        Balancesheet.addTableItem(Balancesheet_goodwill_out);
        Balancesheet.addTableItem(Balancesheet_lt_amor_exp_out);
        Balancesheet.addTableItem(Balancesheet_defer_tax_assets_out);
        Balancesheet.addTableItem(Balancesheet_decr_in_disbur_out);
        Balancesheet.addTableItem(Balancesheet_oth_nca_out);
        Balancesheet.addTableItem(Balancesheet_total_nca_out);
        Balancesheet.addTableItem(Balancesheet_cash_reser_cb_out);
        Balancesheet.addTableItem(Balancesheet_depos_in_oth_bfi_out);
        Balancesheet.addTableItem(Balancesheet_prec_metals_out);
        Balancesheet.addTableItem(Balancesheet_deriv_assets_out);
        Balancesheet.addTableItem(Balancesheet_rr_reins_une_prem_out);
        Balancesheet.addTableItem(Balancesheet_rr_reins_outstd_cla_out);
        Balancesheet.addTableItem(Balancesheet_rr_reins_lins_liab_out);
        Balancesheet.addTableItem(Balancesheet_rr_reins_lthins_liab_out);
        Balancesheet.addTableItem(Balancesheet_refund_depos_out);
        Balancesheet.addTableItem(Balancesheet_ph_pledge_loans_out);
        Balancesheet.addTableItem(Balancesheet_refund_cap_depos_out);
        Balancesheet.addTableItem(Balancesheet_indep_acct_assets_out);
        Balancesheet.addTableItem(Balancesheet_client_depos_out);
        Balancesheet.addTableItem(Balancesheet_client_prov_out);
        Balancesheet.addTableItem(Balancesheet_transac_seat_fee_out);
        Balancesheet.addTableItem(Balancesheet_invest_as_receiv_out);
        Balancesheet.addTableItem(Balancesheet_total_assets_out);
        Balancesheet.addTableItem(Balancesheet_lt_borr_out);
        Balancesheet.addTableItem(Balancesheet_st_borr_out);
        Balancesheet.addTableItem(Balancesheet_cb_borr_out);
        Balancesheet.addTableItem(Balancesheet_depos_ib_deposits_out);
        Balancesheet.addTableItem(Balancesheet_loan_oth_bank_out);
        Balancesheet.addTableItem(Balancesheet_trading_fl_out);
        Balancesheet.addTableItem(Balancesheet_notes_payable_out);
        Balancesheet.addTableItem(Balancesheet_acct_payable_out);
        Balancesheet.addTableItem(Balancesheet_adv_receipts_out);
        Balancesheet.addTableItem(Balancesheet_sold_for_repur_fa_out);
        Balancesheet.addTableItem(Balancesheet_comm_payable_out);
        Balancesheet.addTableItem(Balancesheet_payroll_payable_out);
        Balancesheet.addTableItem(Balancesheet_taxes_payable_out);
        Balancesheet.addTableItem(Balancesheet_int_payable_out);
        Balancesheet.addTableItem(Balancesheet_div_payable_out);
        Balancesheet.addTableItem(Balancesheet_oth_payable_out);
        Balancesheet.addTableItem(Balancesheet_acc_exp_out);
        Balancesheet.addTableItem(Balancesheet_deferred_inc_out);
        Balancesheet.addTableItem(Balancesheet_st_bonds_payable_out);
        Balancesheet.addTableItem(Balancesheet_payable_to_reinsurer_out);
        Balancesheet.addTableItem(Balancesheet_rsrv_insur_cont_out);
        Balancesheet.addTableItem(Balancesheet_acting_trading_sec_out);
        Balancesheet.addTableItem(Balancesheet_acting_uw_sec_out);
        Balancesheet.addTableItem(Balancesheet_non_cur_liab_due_1y_out);
        Balancesheet.addTableItem(Balancesheet_oth_cur_liab_out);
        Balancesheet.addTableItem(Balancesheet_total_cur_liab_out);
        Balancesheet.addTableItem(Balancesheet_bond_payable_out);
        Balancesheet.addTableItem(Balancesheet_lt_payable_out);
        Balancesheet.addTableItem(Balancesheet_specific_payables_out);
        Balancesheet.addTableItem(Balancesheet_estimated_liab_out);
        Balancesheet.addTableItem(Balancesheet_defer_tax_liab_out);
        Balancesheet.addTableItem(Balancesheet_defer_inc_non_cur_liab_out);
        Balancesheet.addTableItem(Balancesheet_oth_ncl_out);
        Balancesheet.addTableItem(Balancesheet_total_ncl_out);
        Balancesheet.addTableItem(Balancesheet_depos_oth_bfi_out);
        Balancesheet.addTableItem(Balancesheet_deriv_liab_out);
        Balancesheet.addTableItem(Balancesheet_depos_out);
        Balancesheet.addTableItem(Balancesheet_agency_bus_liab_out);
        Balancesheet.addTableItem(Balancesheet_oth_liab_out);
        Balancesheet.addTableItem(Balancesheet_prem_receiv_adva_out);
        Balancesheet.addTableItem(Balancesheet_depos_received_out);
        Balancesheet.addTableItem(Balancesheet_ph_invest_out);
        Balancesheet.addTableItem(Balancesheet_reser_une_prem_out);
        Balancesheet.addTableItem(Balancesheet_reser_outstd_claims_out);
        Balancesheet.addTableItem(Balancesheet_reser_lins_liab_out);
        Balancesheet.addTableItem(Balancesheet_reser_lthins_liab_out);
        Balancesheet.addTableItem(Balancesheet_indept_acc_liab_out);
        Balancesheet.addTableItem(Balancesheet_pledge_borr_out);
        Balancesheet.addTableItem(Balancesheet_indem_payable_out);
        Balancesheet.addTableItem(Balancesheet_policy_div_payable_out);
        Balancesheet.addTableItem(Balancesheet_total_liab_out);
        Balancesheet.addTableItem(Balancesheet_treasury_share_out);
        Balancesheet.addTableItem(Balancesheet_ordin_risk_reser_out);
        Balancesheet.addTableItem(Balancesheet_forex_differ_out);
        Balancesheet.addTableItem(Balancesheet_invest_loss_unconf_out);
        Balancesheet.addTableItem(Balancesheet_minority_int_out);
        Balancesheet.addTableItem(Balancesheet_total_hldr_eqy_exc_min_int_out);
        Balancesheet.addTableItem(Balancesheet_total_hldr_eqy_inc_min_int_out);
        Balancesheet.addTableItem(Balancesheet_total_liab_hldr_eqy_out);
        Balancesheet.addTableItem(Balancesheet_lt_payroll_payable_out);
        Balancesheet.addTableItem(Balancesheet_oth_comp_income_out);
        Balancesheet.addTableItem(Balancesheet_oth_eqt_tools_out);
        Balancesheet.addTableItem(Balancesheet_oth_eqt_tools_p_shr_out);
        Balancesheet.addTableItem(Balancesheet_lending_funds_out);
        Balancesheet.addTableItem(Balancesheet_acc_receivable_out);
        Balancesheet.addTableItem(Balancesheet_st_fin_payable_out);
        Balancesheet.addTableItem(Balancesheet_payables_out);
        Balancesheet.addTableItem(Balancesheet_hfs_assets_out);
        Balancesheet.addTableItem(Balancesheet_hfs_sales_out);
        Balancesheet.addTableItem(Balancesheet_update_flag_out);

        SQLite_Tables.add(Balancesheet);
    }


    static {
//  【 table_25_cashflow 】 获取上市公司现金流量表
        DB_Table Cashflow = new DB_Table("cashflow");
        Cashflow.tableIndex = 25;
        Cashflow.viceTableIndex = 1;
        Cashflow.scoreLimit = 500;
        Cashflow.tableDesc = "获取上市公司现金流量表";
        Table_Input_Param Cashflow_ts_code = new Table_Input_Param("ts_code", "str", "");
        Cashflow_ts_code.desc = "股票代码";
        Table_Input_Param Cashflow_ann_date = new Table_Input_Param("ann_date", "str", "");
        Cashflow_ann_date.desc = "公告日期";
        Table_Input_Param Cashflow_start_date = new Table_Input_Param("start_date", "str", "");
        Cashflow_start_date.desc = "公告开始日期";
        Table_Input_Param Cashflow_end_date = new Table_Input_Param("end_date", "str", "");
        Cashflow_end_date.desc = "公告结束日期";
        Table_Input_Param Cashflow_period = new Table_Input_Param("period", "str", "");
        Cashflow_period.desc = "报告期每个季度最后一天的日期比如表示年报";
        Table_Input_Param Cashflow_report_type = new Table_Input_Param("report_type", "str", "");
        Cashflow_report_type.desc = "报告类型见下方详细说明";
        Table_Input_Param Cashflow_comp_type = new Table_Input_Param("comp_type", "str", "");
        Cashflow_comp_type.desc = "公司类型一般工商业银行保险证券";
        Cashflow.addTableInputParam(Cashflow_ts_code);
        Cashflow.addTableInputParam(Cashflow_ann_date);
        Cashflow.addTableInputParam(Cashflow_start_date);
        Cashflow.addTableInputParam(Cashflow_end_date);
        Cashflow.addTableInputParam(Cashflow_period);
        Cashflow.addTableInputParam(Cashflow_report_type);
        Cashflow.addTableInputParam(Cashflow_comp_type);

        Table_Item Cashflow_ts_code_out = new Table_Item("ts_code", SQLITE_TYPE.TEXT, "股票代码");
        Table_Item Cashflow_ann_date_out = new Table_Item("ann_date", SQLITE_TYPE.TEXT, "公告日期");
        Table_Item Cashflow_f_ann_date_out = new Table_Item("f_ann_date", SQLITE_TYPE.TEXT, "实际公告日期");
        Table_Item Cashflow_end_date_out = new Table_Item("end_date", SQLITE_TYPE.TEXT, "报告期");
        Table_Item Cashflow_comp_type_out = new Table_Item("comp_type", SQLITE_TYPE.TEXT, "公司类型");
        Table_Item Cashflow_report_type_out = new Table_Item("report_type", SQLITE_TYPE.TEXT, "报表类型");
        Table_Item Cashflow_net_profit_out = new Table_Item("net_profit", SQLITE_TYPE.REAL, "净利润");
        Table_Item Cashflow_finan_exp_out = new Table_Item("finan_exp", SQLITE_TYPE.REAL, "财务费用");
        Table_Item Cashflow_c_fr_sale_sg_out = new Table_Item("c_fr_sale_sg", SQLITE_TYPE.REAL, "销售商品提供劳务收到的现金");
        Table_Item Cashflow_recp_tax_rends_out = new Table_Item("recp_tax_rends", SQLITE_TYPE.REAL, "收到的税费返还");
        Table_Item Cashflow_n_depos_incr_fi_out = new Table_Item("n_depos_incr_fi", SQLITE_TYPE.REAL, "客户存款和同业存放款项净增加额");
        Table_Item Cashflow_n_incr_loans_cb_out = new Table_Item("n_incr_loans_cb", SQLITE_TYPE.REAL, "向中央银行借款净增加额");
        Table_Item Cashflow_n_inc_borr_oth_fi_out = new Table_Item("n_inc_borr_oth_fi", SQLITE_TYPE.REAL, "向其他金融机构拆入资金净增加额");
        Table_Item Cashflow_prem_fr_orig_contr_out = new Table_Item("prem_fr_orig_contr", SQLITE_TYPE.REAL, "收到原保险合同保费取得的现金");
        Table_Item Cashflow_n_incr_insured_dep_out = new Table_Item("n_incr_insured_dep", SQLITE_TYPE.REAL, "保户储金净增加额");
        Table_Item Cashflow_n_reinsur_prem_out = new Table_Item("n_reinsur_prem", SQLITE_TYPE.REAL, "收到再保业务现金净额");
        Table_Item Cashflow_n_incr_disp_tfa_out = new Table_Item("n_incr_disp_tfa", SQLITE_TYPE.REAL, "处置交易性金融资产净增加额");
        Table_Item Cashflow_ifc_cash_incr_out = new Table_Item("ifc_cash_incr", SQLITE_TYPE.REAL, "收取利息和手续费净增加额");
        Table_Item Cashflow_n_incr_disp_faas_out = new Table_Item("n_incr_disp_faas", SQLITE_TYPE.REAL, "处置可供出售金融资产净增加额");
        Table_Item Cashflow_n_incr_loans_oth_bank_out = new Table_Item("n_incr_loans_oth_bank", SQLITE_TYPE.REAL, "拆入资金净增加额");
        Table_Item Cashflow_n_cap_incr_repur_out = new Table_Item("n_cap_incr_repur", SQLITE_TYPE.REAL, "回购业务资金净增加额");
        Table_Item Cashflow_c_fr_oth_operate_a_out = new Table_Item("c_fr_oth_operate_a", SQLITE_TYPE.REAL, "收到其他与经营活动有关的现金");
        Table_Item Cashflow_c_inf_fr_operate_a_out = new Table_Item("c_inf_fr_operate_a", SQLITE_TYPE.REAL, "经营活动现金流入小计");
        Table_Item Cashflow_c_paid_goods_s_out = new Table_Item("c_paid_goods_s", SQLITE_TYPE.REAL, "购买商品接受劳务支付的现金");
        Table_Item Cashflow_c_paid_to_for_empl_out = new Table_Item("c_paid_to_for_empl", SQLITE_TYPE.REAL, "支付给职工以及为职工支付的现金");
        Table_Item Cashflow_c_paid_for_taxes_out = new Table_Item("c_paid_for_taxes", SQLITE_TYPE.REAL, "支付的各项税费");
        Table_Item Cashflow_n_incr_clt_loan_adv_out = new Table_Item("n_incr_clt_loan_adv", SQLITE_TYPE.REAL, "客户贷款及垫款净增加额");
        Table_Item Cashflow_n_incr_dep_cbob_out = new Table_Item("n_incr_dep_cbob", SQLITE_TYPE.REAL, "存放央行和同业款项净增加额");
        Table_Item Cashflow_c_pay_claims_orig_inco_out = new Table_Item("c_pay_claims_orig_inco", SQLITE_TYPE.REAL, "支付原保险合同赔付款项的现金");
        Table_Item Cashflow_pay_handling_chrg_out = new Table_Item("pay_handling_chrg", SQLITE_TYPE.REAL, "支付手续费的现金");
        Table_Item Cashflow_pay_comm_insur_plcy_out = new Table_Item("pay_comm_insur_plcy", SQLITE_TYPE.REAL, "支付保单红利的现金");
        Table_Item Cashflow_oth_cash_pay_oper_act_out = new Table_Item("oth_cash_pay_oper_act", SQLITE_TYPE.REAL, "支付其他与经营活动有关的现金");
        Table_Item Cashflow_st_cash_out_act_out = new Table_Item("st_cash_out_act", SQLITE_TYPE.REAL, "经营活动现金流出小计");
        Table_Item Cashflow_n_cashflow_act_out = new Table_Item("n_cashflow_act", SQLITE_TYPE.REAL, "经营活动产生的现金流量净额");
        Table_Item Cashflow_oth_recp_ral_inv_act_out = new Table_Item("oth_recp_ral_inv_act", SQLITE_TYPE.REAL, "收到其他与投资活动有关的现金");
        Table_Item Cashflow_c_disp_withdrwl_invest_out = new Table_Item("c_disp_withdrwl_invest", SQLITE_TYPE.REAL, "收回投资收到的现金");
        Table_Item Cashflow_c_recp_return_invest_out = new Table_Item("c_recp_return_invest", SQLITE_TYPE.REAL, "取得投资收益收到的现金");
        Table_Item Cashflow_n_recp_disp_fiolta_out = new Table_Item("n_recp_disp_fiolta", SQLITE_TYPE.REAL, "处置固定资产无形资产和其他长期资产收回的现金净额");
        Table_Item Cashflow_n_recp_disp_sobu_out = new Table_Item("n_recp_disp_sobu", SQLITE_TYPE.REAL, "处置子公司及其他营业单位收到的现金净额");
        Table_Item Cashflow_stot_inflows_inv_act_out = new Table_Item("stot_inflows_inv_act", SQLITE_TYPE.REAL, "投资活动现金流入小计");
        Table_Item Cashflow_c_pay_acq_const_fiolta_out = new Table_Item("c_pay_acq_const_fiolta", SQLITE_TYPE.REAL, "购建固定资产无形资产和其他长期资产支付的现金");
        Table_Item Cashflow_c_paid_invest_out = new Table_Item("c_paid_invest", SQLITE_TYPE.REAL, "投资支付的现金");
        Table_Item Cashflow_n_disp_subs_oth_biz_out = new Table_Item("n_disp_subs_oth_biz", SQLITE_TYPE.REAL, "取得子公司及其他营业单位支付的现金净额");
        Table_Item Cashflow_oth_pay_ral_inv_act_out = new Table_Item("oth_pay_ral_inv_act", SQLITE_TYPE.REAL, "支付其他与投资活动有关的现金");
        Table_Item Cashflow_n_incr_pledge_loan_out = new Table_Item("n_incr_pledge_loan", SQLITE_TYPE.REAL, "质押贷款净增加额");
        Table_Item Cashflow_stot_out_inv_act_out = new Table_Item("stot_out_inv_act", SQLITE_TYPE.REAL, "投资活动现金流出小计");
        Table_Item Cashflow_n_cashflow_inv_act_out = new Table_Item("n_cashflow_inv_act", SQLITE_TYPE.REAL, "投资活动产生的现金流量净额");
        Table_Item Cashflow_c_recp_borrow_out = new Table_Item("c_recp_borrow", SQLITE_TYPE.REAL, "取得借款收到的现金");
        Table_Item Cashflow_proc_issue_bonds_out = new Table_Item("proc_issue_bonds", SQLITE_TYPE.REAL, "发行债券收到的现金");
        Table_Item Cashflow_oth_cash_recp_ral_fnc_act_out = new Table_Item("oth_cash_recp_ral_fnc_act", SQLITE_TYPE.REAL, "收到其他与筹资活动有关的现金");
        Table_Item Cashflow_stot_cash_in_fnc_act_out = new Table_Item("stot_cash_in_fnc_act", SQLITE_TYPE.REAL, "筹资活动现金流入小计");
        Table_Item Cashflow_free_cashflow_out = new Table_Item("free_cashflow", SQLITE_TYPE.REAL, "企业自由现金流量");
        Table_Item Cashflow_c_prepay_amt_borr_out = new Table_Item("c_prepay_amt_borr", SQLITE_TYPE.REAL, "偿还债务支付的现金");
        Table_Item Cashflow_c_pay_dist_dpcp_int_exp_out = new Table_Item("c_pay_dist_dpcp_int_exp", SQLITE_TYPE.REAL, "分配股利利润或偿付利息支付的现金");
        Table_Item Cashflow_incl_dvd_profit_paid_sc_ms_out = new Table_Item("incl_dvd_profit_paid_sc_ms", SQLITE_TYPE.REAL, "其中子公司支付给少数股东的股利利润");
        Table_Item Cashflow_oth_cashpay_ral_fnc_act_out = new Table_Item("oth_cashpay_ral_fnc_act", SQLITE_TYPE.REAL, "支付其他与筹资活动有关的现金");
        Table_Item Cashflow_stot_cashout_fnc_act_out = new Table_Item("stot_cashout_fnc_act", SQLITE_TYPE.REAL, "筹资活动现金流出小计");
        Table_Item Cashflow_n_cash_flows_fnc_act_out = new Table_Item("n_cash_flows_fnc_act", SQLITE_TYPE.REAL, "筹资活动产生的现金流量净额");
        Table_Item Cashflow_eff_fx_flu_cash_out = new Table_Item("eff_fx_flu_cash", SQLITE_TYPE.REAL, "汇率变动对现金的影响");
        Table_Item Cashflow_n_incr_cash_cash_equ_out = new Table_Item("n_incr_cash_cash_equ", SQLITE_TYPE.REAL, "现金及现金等价物净增加额");
        Table_Item Cashflow_c_cash_equ_beg_period_out = new Table_Item("c_cash_equ_beg_period", SQLITE_TYPE.REAL, "期初现金及现金等价物余额");
        Table_Item Cashflow_c_cash_equ_end_period_out = new Table_Item("c_cash_equ_end_period", SQLITE_TYPE.REAL, "期末现金及现金等价物余额");
        Table_Item Cashflow_c_recp_cap_contrib_out = new Table_Item("c_recp_cap_contrib", SQLITE_TYPE.REAL, "吸收投资收到的现金");
        Table_Item Cashflow_incl_cash_rec_saims_out = new Table_Item("incl_cash_rec_saims", SQLITE_TYPE.REAL, "其中子公司吸收少数股东投资收到的现金");
        Table_Item Cashflow_uncon_invest_loss_out = new Table_Item("uncon_invest_loss", SQLITE_TYPE.REAL, "未确认投资损失");
        Table_Item Cashflow_prov_depr_assets_out = new Table_Item("prov_depr_assets", SQLITE_TYPE.REAL, "加资产减值准备");
        Table_Item Cashflow_depr_fa_coga_dpba_out = new Table_Item("depr_fa_coga_dpba", SQLITE_TYPE.REAL, "固定资产折旧油气资产折耗生产性生物资产折旧");
        Table_Item Cashflow_amort_intang_assets_out = new Table_Item("amort_intang_assets", SQLITE_TYPE.REAL, "无形资产摊销");
        Table_Item Cashflow_lt_amort_deferred_exp_out = new Table_Item("lt_amort_deferred_exp", SQLITE_TYPE.REAL, "长期待摊费用摊销");
        Table_Item Cashflow_decr_deferred_exp_out = new Table_Item("decr_deferred_exp", SQLITE_TYPE.REAL, "待摊费用减少");
        Table_Item Cashflow_incr_acc_exp_out = new Table_Item("incr_acc_exp", SQLITE_TYPE.REAL, "预提费用增加");
        Table_Item Cashflow_loss_disp_fiolta_out = new Table_Item("loss_disp_fiolta", SQLITE_TYPE.REAL, "处置固定无形资产和其他长期资产的损失");
        Table_Item Cashflow_loss_scr_fa_out = new Table_Item("loss_scr_fa", SQLITE_TYPE.REAL, "固定资产报废损失");
        Table_Item Cashflow_loss_fv_chg_out = new Table_Item("loss_fv_chg", SQLITE_TYPE.REAL, "公允价值变动损失");
        Table_Item Cashflow_invest_loss_out = new Table_Item("invest_loss", SQLITE_TYPE.REAL, "投资损失");
        Table_Item Cashflow_decr_def_inc_tax_assets_out = new Table_Item("decr_def_inc_tax_assets", SQLITE_TYPE.REAL, "递延所得税资产减少");
        Table_Item Cashflow_incr_def_inc_tax_liab_out = new Table_Item("incr_def_inc_tax_liab", SQLITE_TYPE.REAL, "递延所得税负债增加");
        Table_Item Cashflow_decr_inventories_out = new Table_Item("decr_inventories", SQLITE_TYPE.REAL, "存货的减少");
        Table_Item Cashflow_decr_oper_payable_out = new Table_Item("decr_oper_payable", SQLITE_TYPE.REAL, "经营性应收项目的减少");
        Table_Item Cashflow_incr_oper_payable_out = new Table_Item("incr_oper_payable", SQLITE_TYPE.REAL, "经营性应付项目的增加");
        Table_Item Cashflow_others_out = new Table_Item("others", SQLITE_TYPE.REAL, "其他");
        Table_Item Cashflow_im_net_cashflow_oper_act_out = new Table_Item("im_net_cashflow_oper_act", SQLITE_TYPE.REAL, "经营活动产生的现金流量净额间接法");
        Table_Item Cashflow_conv_debt_into_cap_out = new Table_Item("conv_debt_into_cap", SQLITE_TYPE.REAL, "债务转为资本");
        Table_Item Cashflow_conv_copbonds_due_within_1y_out = new Table_Item("conv_copbonds_due_within_1y", SQLITE_TYPE.REAL, "一年内到期的可转换公司债券");
        Table_Item Cashflow_fa_fnc_leases_out = new Table_Item("fa_fnc_leases", SQLITE_TYPE.REAL, "融资租入固定资产");
        Table_Item Cashflow_end_bal_cash_out = new Table_Item("end_bal_cash", SQLITE_TYPE.REAL, "现金的期末余额");
        Table_Item Cashflow_beg_bal_cash_out = new Table_Item("beg_bal_cash", SQLITE_TYPE.REAL, "减现金的期初余额");
        Table_Item Cashflow_end_bal_cash_equ_out = new Table_Item("end_bal_cash_equ", SQLITE_TYPE.REAL, "加现金等价物的期末余额");
        Table_Item Cashflow_beg_bal_cash_equ_out = new Table_Item("beg_bal_cash_equ", SQLITE_TYPE.REAL, "减现金等价物的期初余额");
        Table_Item Cashflow_im_n_incr_cash_equ_out = new Table_Item("im_n_incr_cash_equ", SQLITE_TYPE.REAL, "现金及现金等价物净增加额间接法");
        Table_Item Cashflow_update_flag_out = new Table_Item("update_flag", SQLITE_TYPE.TEXT, "更新标识");
        Cashflow.addTableItem(Cashflow_ts_code_out);
        Cashflow.addTableItem(Cashflow_ann_date_out);
        Cashflow.addTableItem(Cashflow_f_ann_date_out);
        Cashflow.addTableItem(Cashflow_end_date_out);
        Cashflow.addTableItem(Cashflow_comp_type_out);
        Cashflow.addTableItem(Cashflow_report_type_out);
        Cashflow.addTableItem(Cashflow_net_profit_out);
        Cashflow.addTableItem(Cashflow_finan_exp_out);
        Cashflow.addTableItem(Cashflow_c_fr_sale_sg_out);
        Cashflow.addTableItem(Cashflow_recp_tax_rends_out);
        Cashflow.addTableItem(Cashflow_n_depos_incr_fi_out);
        Cashflow.addTableItem(Cashflow_n_incr_loans_cb_out);
        Cashflow.addTableItem(Cashflow_n_inc_borr_oth_fi_out);
        Cashflow.addTableItem(Cashflow_prem_fr_orig_contr_out);
        Cashflow.addTableItem(Cashflow_n_incr_insured_dep_out);
        Cashflow.addTableItem(Cashflow_n_reinsur_prem_out);
        Cashflow.addTableItem(Cashflow_n_incr_disp_tfa_out);
        Cashflow.addTableItem(Cashflow_ifc_cash_incr_out);
        Cashflow.addTableItem(Cashflow_n_incr_disp_faas_out);
        Cashflow.addTableItem(Cashflow_n_incr_loans_oth_bank_out);
        Cashflow.addTableItem(Cashflow_n_cap_incr_repur_out);
        Cashflow.addTableItem(Cashflow_c_fr_oth_operate_a_out);
        Cashflow.addTableItem(Cashflow_c_inf_fr_operate_a_out);
        Cashflow.addTableItem(Cashflow_c_paid_goods_s_out);
        Cashflow.addTableItem(Cashflow_c_paid_to_for_empl_out);
        Cashflow.addTableItem(Cashflow_c_paid_for_taxes_out);
        Cashflow.addTableItem(Cashflow_n_incr_clt_loan_adv_out);
        Cashflow.addTableItem(Cashflow_n_incr_dep_cbob_out);
        Cashflow.addTableItem(Cashflow_c_pay_claims_orig_inco_out);
        Cashflow.addTableItem(Cashflow_pay_handling_chrg_out);
        Cashflow.addTableItem(Cashflow_pay_comm_insur_plcy_out);
        Cashflow.addTableItem(Cashflow_oth_cash_pay_oper_act_out);
        Cashflow.addTableItem(Cashflow_st_cash_out_act_out);
        Cashflow.addTableItem(Cashflow_n_cashflow_act_out);
        Cashflow.addTableItem(Cashflow_oth_recp_ral_inv_act_out);
        Cashflow.addTableItem(Cashflow_c_disp_withdrwl_invest_out);
        Cashflow.addTableItem(Cashflow_c_recp_return_invest_out);
        Cashflow.addTableItem(Cashflow_n_recp_disp_fiolta_out);
        Cashflow.addTableItem(Cashflow_n_recp_disp_sobu_out);
        Cashflow.addTableItem(Cashflow_stot_inflows_inv_act_out);
        Cashflow.addTableItem(Cashflow_c_pay_acq_const_fiolta_out);
        Cashflow.addTableItem(Cashflow_c_paid_invest_out);
        Cashflow.addTableItem(Cashflow_n_disp_subs_oth_biz_out);
        Cashflow.addTableItem(Cashflow_oth_pay_ral_inv_act_out);
        Cashflow.addTableItem(Cashflow_n_incr_pledge_loan_out);
        Cashflow.addTableItem(Cashflow_stot_out_inv_act_out);
        Cashflow.addTableItem(Cashflow_n_cashflow_inv_act_out);
        Cashflow.addTableItem(Cashflow_c_recp_borrow_out);
        Cashflow.addTableItem(Cashflow_proc_issue_bonds_out);
        Cashflow.addTableItem(Cashflow_oth_cash_recp_ral_fnc_act_out);
        Cashflow.addTableItem(Cashflow_stot_cash_in_fnc_act_out);
        Cashflow.addTableItem(Cashflow_free_cashflow_out);
        Cashflow.addTableItem(Cashflow_c_prepay_amt_borr_out);
        Cashflow.addTableItem(Cashflow_c_pay_dist_dpcp_int_exp_out);
        Cashflow.addTableItem(Cashflow_incl_dvd_profit_paid_sc_ms_out);
        Cashflow.addTableItem(Cashflow_oth_cashpay_ral_fnc_act_out);
        Cashflow.addTableItem(Cashflow_stot_cashout_fnc_act_out);
        Cashflow.addTableItem(Cashflow_n_cash_flows_fnc_act_out);
        Cashflow.addTableItem(Cashflow_eff_fx_flu_cash_out);
        Cashflow.addTableItem(Cashflow_n_incr_cash_cash_equ_out);
        Cashflow.addTableItem(Cashflow_c_cash_equ_beg_period_out);
        Cashflow.addTableItem(Cashflow_c_cash_equ_end_period_out);
        Cashflow.addTableItem(Cashflow_c_recp_cap_contrib_out);
        Cashflow.addTableItem(Cashflow_incl_cash_rec_saims_out);
        Cashflow.addTableItem(Cashflow_uncon_invest_loss_out);
        Cashflow.addTableItem(Cashflow_prov_depr_assets_out);
        Cashflow.addTableItem(Cashflow_depr_fa_coga_dpba_out);
        Cashflow.addTableItem(Cashflow_amort_intang_assets_out);
        Cashflow.addTableItem(Cashflow_lt_amort_deferred_exp_out);
        Cashflow.addTableItem(Cashflow_decr_deferred_exp_out);
        Cashflow.addTableItem(Cashflow_incr_acc_exp_out);
        Cashflow.addTableItem(Cashflow_loss_disp_fiolta_out);
        Cashflow.addTableItem(Cashflow_loss_scr_fa_out);
        Cashflow.addTableItem(Cashflow_loss_fv_chg_out);
        Cashflow.addTableItem(Cashflow_invest_loss_out);
        Cashflow.addTableItem(Cashflow_decr_def_inc_tax_assets_out);
        Cashflow.addTableItem(Cashflow_incr_def_inc_tax_liab_out);
        Cashflow.addTableItem(Cashflow_decr_inventories_out);
        Cashflow.addTableItem(Cashflow_decr_oper_payable_out);
        Cashflow.addTableItem(Cashflow_incr_oper_payable_out);
        Cashflow.addTableItem(Cashflow_others_out);
        Cashflow.addTableItem(Cashflow_im_net_cashflow_oper_act_out);
        Cashflow.addTableItem(Cashflow_conv_debt_into_cap_out);
        Cashflow.addTableItem(Cashflow_conv_copbonds_due_within_1y_out);
        Cashflow.addTableItem(Cashflow_fa_fnc_leases_out);
        Cashflow.addTableItem(Cashflow_end_bal_cash_out);
        Cashflow.addTableItem(Cashflow_beg_bal_cash_out);
        Cashflow.addTableItem(Cashflow_end_bal_cash_equ_out);
        Cashflow.addTableItem(Cashflow_beg_bal_cash_equ_out);
        Cashflow.addTableItem(Cashflow_im_n_incr_cash_equ_out);
        Cashflow.addTableItem(Cashflow_update_flag_out);

        SQLite_Tables.add(Cashflow);
    }


    static {
//  【 table_26_forecast】 获取业绩预告数据
        DB_Table Forecast = new DB_Table("forecast");
        Forecast.tableIndex = 26;
        Forecast.viceTableIndex = 1;
        Forecast.scoreLimit = 600;
        Forecast.tableDesc = "获取业绩预告数据";
        Table_Input_Param Forecast_ts_code = new Table_Input_Param("ts_code", "str", "");
        Forecast_ts_code.desc = "股票代码二选一";
        Table_Input_Param Forecast_ann_date = new Table_Input_Param("ann_date", "str", "");
        Forecast_ann_date.desc = "公告日期二选一";
        Table_Input_Param Forecast_start_date = new Table_Input_Param("start_date", "str", "");
        Forecast_start_date.desc = "公告开始日期";
        Table_Input_Param Forecast_end_date = new Table_Input_Param("end_date", "str", "");
        Forecast_end_date.desc = "公告结束日期";
        Table_Input_Param Forecast_period = new Table_Input_Param("period", "str", "");
        Forecast_period.desc = "报告期每个季度最后一天的日期比如表示年报";
        Table_Input_Param Forecast_type = new Table_Input_Param("type", "str", "");
        Forecast_type.desc = "预告类型预增预减扭亏首亏续亏续盈略增略减";
        Forecast.addTableInputParam(Forecast_ts_code);
        Forecast.addTableInputParam(Forecast_ann_date);
        Forecast.addTableInputParam(Forecast_start_date);
        Forecast.addTableInputParam(Forecast_end_date);
        Forecast.addTableInputParam(Forecast_period);
        Forecast.addTableInputParam(Forecast_type);

        Table_Item Forecast_ts_code_out = new Table_Item("ts_code", SQLITE_TYPE.TEXT, "股票代码");
        Table_Item Forecast_ann_date_out = new Table_Item("ann_date", SQLITE_TYPE.TEXT, "公告日期");
        Table_Item Forecast_end_date_out = new Table_Item("end_date", SQLITE_TYPE.TEXT, "报告期");
        Table_Item Forecast_type_out = new Table_Item("type", SQLITE_TYPE.TEXT, "业绩预告类型预增预减扭亏首亏续亏续盈略增略减");
        Table_Item Forecast_p_change_min_out = new Table_Item("p_change_min", SQLITE_TYPE.REAL, "预告净利润变动幅度下限");
        Table_Item Forecast_p_change_max_out = new Table_Item("p_change_max", SQLITE_TYPE.REAL, "预告净利润变动幅度上限");
        Table_Item Forecast_net_profit_min_out = new Table_Item("net_profit_min", SQLITE_TYPE.REAL, "预告净利润下限万元");
        Table_Item Forecast_net_profit_max_out = new Table_Item("net_profit_max", SQLITE_TYPE.REAL, "预告净利润上限万元");
        Table_Item Forecast_last_parent_net_out = new Table_Item("last_parent_net", SQLITE_TYPE.REAL, "上年同期归属母公司净利润");
        Table_Item Forecast_first_ann_date_out = new Table_Item("first_ann_date", SQLITE_TYPE.TEXT, "首次公告日");
        Table_Item Forecast_summary_out = new Table_Item("summary", SQLITE_TYPE.TEXT, "业绩预告摘要");
        Table_Item Forecast_change_reason_out = new Table_Item("change_reason", SQLITE_TYPE.TEXT, "业绩变动原因");
        Forecast.addTableItem(Forecast_ts_code_out);
        Forecast.addTableItem(Forecast_ann_date_out);
        Forecast.addTableItem(Forecast_end_date_out);
        Forecast.addTableItem(Forecast_type_out);
        Forecast.addTableItem(Forecast_p_change_min_out);
        Forecast.addTableItem(Forecast_p_change_max_out);
        Forecast.addTableItem(Forecast_net_profit_min_out);
        Forecast.addTableItem(Forecast_net_profit_max_out);
        Forecast.addTableItem(Forecast_last_parent_net_out);
        Forecast.addTableItem(Forecast_first_ann_date_out);
        Forecast.addTableItem(Forecast_summary_out);
        Forecast.addTableItem(Forecast_change_reason_out);

        SQLite_Tables.add(Forecast);
    }


    static {
//  【 table_27_express 】 获取上市公司业绩快报
        DB_Table Express = new DB_Table("express");
        Express.tableIndex = 27;
        Express.viceTableIndex = 1;
        Express.scoreLimit = 500;
        Express.tableDesc = "获取上市公司业绩快报";
        Table_Input_Param Express_ts_code = new Table_Input_Param("ts_code", "str", "");
        Express_ts_code.desc = "股票代码";
        Table_Input_Param Express_ann_date = new Table_Input_Param("ann_date", "str", "");
        Express_ann_date.desc = "公告日期";
        Table_Input_Param Express_start_date = new Table_Input_Param("start_date", "str", "");
        Express_start_date.desc = "公告开始日期";
        Table_Input_Param Express_end_date = new Table_Input_Param("end_date", "str", "");
        Express_end_date.desc = "公告结束日期";
        Table_Input_Param Express_period = new Table_Input_Param("period", "str", "");
        Express_period.desc = "报告期每个季度最后一天的日期比如表示年报";
        Express.addTableInputParam(Express_ts_code);
        Express.addTableInputParam(Express_ann_date);
        Express.addTableInputParam(Express_start_date);
        Express.addTableInputParam(Express_end_date);
        Express.addTableInputParam(Express_period);

        Table_Item Express_ts_code_out = new Table_Item("ts_code", SQLITE_TYPE.TEXT, "股票代码");
        Table_Item Express_ann_date_out = new Table_Item("ann_date", SQLITE_TYPE.TEXT, "公告日期");
        Table_Item Express_end_date_out = new Table_Item("end_date", SQLITE_TYPE.TEXT, "报告期");
        Table_Item Express_revenue_out = new Table_Item("revenue", SQLITE_TYPE.REAL, "营业收入元");
        Table_Item Express_operate_profit_out = new Table_Item("operate_profit", SQLITE_TYPE.REAL, "营业利润元");
        Table_Item Express_total_profit_out = new Table_Item("total_profit", SQLITE_TYPE.REAL, "利润总额元");
        Table_Item Express_n_income_out = new Table_Item("n_income", SQLITE_TYPE.REAL, "净利润元");
        Table_Item Express_total_assets_out = new Table_Item("total_assets", SQLITE_TYPE.REAL, "总资产元");
        Table_Item Express_total_hldr_eqy_exc_min_int_out = new Table_Item("total_hldr_eqy_exc_min_int", SQLITE_TYPE.REAL, "股东权益合计不含少数股东权益元");
        Table_Item Express_diluted_eps_out = new Table_Item("diluted_eps", SQLITE_TYPE.REAL, "每股收益摊薄元");
        Table_Item Express_diluted_roe_out = new Table_Item("diluted_roe", SQLITE_TYPE.REAL, "净资产收益率摊薄");
        Table_Item Express_yoy_net_profit_out = new Table_Item("yoy_net_profit", SQLITE_TYPE.REAL, "去年同期修正后净利润");
        Table_Item Express_bps_out = new Table_Item("bps", SQLITE_TYPE.REAL, "每股净资产");
        Table_Item Express_yoy_sales_out = new Table_Item("yoy_sales", SQLITE_TYPE.REAL, "同比增长率营业收入");
        Table_Item Express_yoy_op_out = new Table_Item("yoy_op", SQLITE_TYPE.REAL, "同比增长率营业利润");
        Table_Item Express_yoy_tp_out = new Table_Item("yoy_tp", SQLITE_TYPE.REAL, "同比增长率利润总额");
        Table_Item Express_yoy_dedu_np_out = new Table_Item("yoy_dedu_np", SQLITE_TYPE.REAL, "同比增长率归属母公司股东的净利润");
        Table_Item Express_yoy_eps_out = new Table_Item("yoy_eps", SQLITE_TYPE.REAL, "同比增长率基本每股收益");
        Table_Item Express_yoy_roe_out = new Table_Item("yoy_roe", SQLITE_TYPE.REAL, "同比增减加权平均净资产收益率");
        Table_Item Express_growth_assets_out = new Table_Item("growth_assets", SQLITE_TYPE.REAL, "比年初增长率总资产");
        Table_Item Express_yoy_equity_out = new Table_Item("yoy_equity", SQLITE_TYPE.REAL, "比年初增长率归属母公司的股东权益");
        Table_Item Express_growth_bps_out = new Table_Item("growth_bps", SQLITE_TYPE.REAL, "比年初增长率归属于母公司股东的每股净资产");
        Table_Item Express_or_last_year_out = new Table_Item("or_last_year", SQLITE_TYPE.REAL, "去年同期营业收入");
        Table_Item Express_op_last_year_out = new Table_Item("op_last_year", SQLITE_TYPE.REAL, "去年同期营业利润");
        Table_Item Express_tp_last_year_out = new Table_Item("tp_last_year", SQLITE_TYPE.REAL, "去年同期利润总额");
        Table_Item Express_np_last_year_out = new Table_Item("np_last_year", SQLITE_TYPE.REAL, "去年同期净利润");
        Table_Item Express_eps_last_year_out = new Table_Item("eps_last_year", SQLITE_TYPE.REAL, "去年同期每股收益");
        Table_Item Express_open_net_assets_out = new Table_Item("open_net_assets", SQLITE_TYPE.REAL, "期初净资产");
        Table_Item Express_open_bps_out = new Table_Item("open_bps", SQLITE_TYPE.REAL, "期初每股净资产");
        Table_Item Express_perf_summary_out = new Table_Item("perf_summary", SQLITE_TYPE.TEXT, "业绩简要说明");
        Table_Item Express_is_audit_out = new Table_Item("is_audit", SQLITE_TYPE.INT, "是否审计是否");
        Table_Item Express_remark_out = new Table_Item("remark", SQLITE_TYPE.TEXT, "备注");
        Express.addTableItem(Express_ts_code_out);
        Express.addTableItem(Express_ann_date_out);
        Express.addTableItem(Express_end_date_out);
        Express.addTableItem(Express_revenue_out);
        Express.addTableItem(Express_operate_profit_out);
        Express.addTableItem(Express_total_profit_out);
        Express.addTableItem(Express_n_income_out);
        Express.addTableItem(Express_total_assets_out);
        Express.addTableItem(Express_total_hldr_eqy_exc_min_int_out);
        Express.addTableItem(Express_diluted_eps_out);
        Express.addTableItem(Express_diluted_roe_out);
        Express.addTableItem(Express_yoy_net_profit_out);
        Express.addTableItem(Express_bps_out);
        Express.addTableItem(Express_yoy_sales_out);
        Express.addTableItem(Express_yoy_op_out);
        Express.addTableItem(Express_yoy_tp_out);
        Express.addTableItem(Express_yoy_dedu_np_out);
        Express.addTableItem(Express_yoy_eps_out);
        Express.addTableItem(Express_yoy_roe_out);
        Express.addTableItem(Express_growth_assets_out);
        Express.addTableItem(Express_yoy_equity_out);
        Express.addTableItem(Express_growth_bps_out);
        Express.addTableItem(Express_or_last_year_out);
        Express.addTableItem(Express_op_last_year_out);
        Express.addTableItem(Express_tp_last_year_out);
        Express.addTableItem(Express_np_last_year_out);
        Express.addTableItem(Express_eps_last_year_out);
        Express.addTableItem(Express_open_net_assets_out);
        Express.addTableItem(Express_open_bps_out);
        Express.addTableItem(Express_perf_summary_out);
        Express.addTableItem(Express_is_audit_out);
        Express.addTableItem(Express_remark_out);

        SQLite_Tables.add(Express);
    }


    static {
//  【 table_28_dividend 】 分红送股数据
        DB_Table Dividend = new DB_Table("dividend");
        Dividend.tableIndex = 28;
        Dividend.viceTableIndex = 1;
        Dividend.tableDesc = "分红送股数据";
        Dividend.scoreLimit = 300;
        Table_Input_Param Dividend_ann_date = new Table_Input_Param("ann_date", "str", "");
        Dividend_ann_date.desc = "公告日";
        Table_Input_Param Dividend_record_date = new Table_Input_Param("record_date", "str", "");
        Dividend_record_date.desc = "股权登记日期";
        Table_Input_Param Dividend_ex_date = new Table_Input_Param("ex_date", "str", "");
        Dividend_ex_date.desc = "除权除息日";
        Table_Input_Param Dividend_imp_ann_date = new Table_Input_Param("imp_ann_date", "str", "");
        Dividend_imp_ann_date.desc = "实施公告日";
        Dividend.addTableInputParam(Dividend_ann_date);
        Dividend.addTableInputParam(Dividend_record_date);
        Dividend.addTableInputParam(Dividend_ex_date);
        Dividend.addTableInputParam(Dividend_imp_ann_date);

        Table_Item Dividend_ts_code_out = new Table_Item("ts_code", SQLITE_TYPE.TEXT, "代码");
        Table_Item Dividend_end_date_out = new Table_Item("end_date", SQLITE_TYPE.TEXT, "分红年度");
        Table_Item Dividend_ann_date_out = new Table_Item("ann_date", SQLITE_TYPE.TEXT, "预案公告日");
        Table_Item Dividend_div_proc_out = new Table_Item("div_proc", SQLITE_TYPE.TEXT, "实施进度");
        Table_Item Dividend_stk_div_out = new Table_Item("stk_div", SQLITE_TYPE.REAL, "每股送转");
        Table_Item Dividend_stk_bo_rate_out = new Table_Item("stk_bo_rate", SQLITE_TYPE.REAL, "每股送股比例");
        Table_Item Dividend_stk_co_rate_out = new Table_Item("stk_co_rate", SQLITE_TYPE.REAL, "每股转增比例");
        Table_Item Dividend_cash_div_out = new Table_Item("cash_div", SQLITE_TYPE.REAL, "每股分红税后");
        Table_Item Dividend_cash_div_tax_out = new Table_Item("cash_div_tax", SQLITE_TYPE.REAL, "每股分红税前");
        Table_Item Dividend_record_date_out = new Table_Item("record_date", SQLITE_TYPE.TEXT, "股权登记日");
        Table_Item Dividend_ex_date_out = new Table_Item("ex_date", SQLITE_TYPE.TEXT, "除权除息日");
        Table_Item Dividend_pay_date_out = new Table_Item("pay_date", SQLITE_TYPE.TEXT, "派息日");
        Table_Item Dividend_div_listdate_out = new Table_Item("div_listdate", SQLITE_TYPE.TEXT, "红股上市日");
        Table_Item Dividend_imp_ann_date_out = new Table_Item("imp_ann_date", SQLITE_TYPE.TEXT, "实施公告日");
        Table_Item Dividend_base_date_out = new Table_Item("base_date", SQLITE_TYPE.TEXT, "基准日");
        Table_Item Dividend_base_share_out = new Table_Item("base_share", SQLITE_TYPE.REAL, "基准股本万");
        Dividend.addTableItem(Dividend_ts_code_out);
        Dividend.addTableItem(Dividend_end_date_out);
        Dividend.addTableItem(Dividend_ann_date_out);
        Dividend.addTableItem(Dividend_div_proc_out);
        Dividend.addTableItem(Dividend_stk_div_out);
        Dividend.addTableItem(Dividend_stk_bo_rate_out);
        Dividend.addTableItem(Dividend_stk_co_rate_out);
        Dividend.addTableItem(Dividend_cash_div_out);
        Dividend.addTableItem(Dividend_cash_div_tax_out);
        Dividend.addTableItem(Dividend_record_date_out);
        Dividend.addTableItem(Dividend_ex_date_out);
        Dividend.addTableItem(Dividend_pay_date_out);
        Dividend.addTableItem(Dividend_div_listdate_out);
        Dividend.addTableItem(Dividend_imp_ann_date_out);
        Dividend.addTableItem(Dividend_base_date_out);
        Dividend.addTableItem(Dividend_base_share_out);

        SQLite_Tables.add(Dividend);
    }


    static {
//  【 table_29_fina_indicator 】 获取上市公司财务指标数据，为避免服务器压力，现阶段每次请求最多返回60条记录，可通过设置日期多次请求获取更多数据。
        DB_Table Fina_Indicator = new DB_Table("fina_indicator");
        Fina_Indicator.tableIndex = 29;
        Fina_Indicator.viceTableIndex = 1;
        Fina_Indicator.scoreLimit = 500;
        Fina_Indicator.tableDesc = "获取上市公司财务指标数据，为避免服务器压力，现阶段每次请求最多返回60条记录，可通过设置日期多次请求获取更多数据。";
        Table_Input_Param Fina_Indicator_ann_date = new Table_Input_Param("ann_date", "str", "");
        Fina_Indicator_ann_date.desc = "公告日期";
        Table_Input_Param Fina_Indicator_start_date = new Table_Input_Param("start_date", "str", "");
        Fina_Indicator_start_date.desc = "报告期开始日期";
        Table_Input_Param Fina_Indicator_end_date = new Table_Input_Param("end_date", "str", "");
        Fina_Indicator_end_date.desc = "报告期结束日期";
        Table_Input_Param Fina_Indicator_period = new Table_Input_Param("period", "str", "");
        Fina_Indicator_period.desc = "报告期每个季度最后一天的日期比如表示年报";
        Fina_Indicator.addTableInputParam(Fina_Indicator_ann_date);
        Fina_Indicator.addTableInputParam(Fina_Indicator_start_date);
        Fina_Indicator.addTableInputParam(Fina_Indicator_end_date);
        Fina_Indicator.addTableInputParam(Fina_Indicator_period);

        Table_Item Fina_Indicator_ts_code_out = new Table_Item("ts_code", SQLITE_TYPE.TEXT, "代码");
        Table_Item Fina_Indicator_ann_date_out = new Table_Item("ann_date", SQLITE_TYPE.TEXT, "公告日期");
        Table_Item Fina_Indicator_end_date_out = new Table_Item("end_date", SQLITE_TYPE.TEXT, "报告期");
        Table_Item Fina_Indicator_eps_out = new Table_Item("eps", SQLITE_TYPE.REAL, "基本每股收益");
        Table_Item Fina_Indicator_dt_eps_out = new Table_Item("dt_eps", SQLITE_TYPE.REAL, "稀释每股收益");
        Table_Item Fina_Indicator_total_revenue_ps_out = new Table_Item("total_revenue_ps", SQLITE_TYPE.REAL, "每股营业总收入");
        Table_Item Fina_Indicator_revenue_ps_out = new Table_Item("revenue_ps", SQLITE_TYPE.REAL, "每股营业收入");
        Table_Item Fina_Indicator_capital_rese_ps_out = new Table_Item("capital_rese_ps", SQLITE_TYPE.REAL, "每股资本公积");
        Table_Item Fina_Indicator_surplus_rese_ps_out = new Table_Item("surplus_rese_ps", SQLITE_TYPE.REAL, "每股盈余公积");
        Table_Item Fina_Indicator_undist_profit_ps_out = new Table_Item("undist_profit_ps", SQLITE_TYPE.REAL, "每股未分配利润");
        Table_Item Fina_Indicator_extra_item_out = new Table_Item("extra_item", SQLITE_TYPE.REAL, "非经常性损益");
        Table_Item Fina_Indicator_profit_dedt_out = new Table_Item("profit_dedt", SQLITE_TYPE.REAL, "扣除非经常性损益后的净利润");
        Table_Item Fina_Indicator_gross_margin_out = new Table_Item("gross_margin", SQLITE_TYPE.REAL, "毛利");
        Table_Item Fina_Indicator_current_ratio_out = new Table_Item("current_ratio", SQLITE_TYPE.REAL, "流动比率");
        Table_Item Fina_Indicator_quick_ratio_out = new Table_Item("quick_ratio", SQLITE_TYPE.REAL, "速动比率");
        Table_Item Fina_Indicator_cash_ratio_out = new Table_Item("cash_ratio", SQLITE_TYPE.REAL, "保守速动比率");
        Table_Item Fina_Indicator_invturn_days_out = new Table_Item("invturn_days", SQLITE_TYPE.REAL, "存货周转天数");
        Table_Item Fina_Indicator_arturn_days_out = new Table_Item("arturn_days", SQLITE_TYPE.REAL, "应收账款周转天数");
        Table_Item Fina_Indicator_inv_turn_out = new Table_Item("inv_turn", SQLITE_TYPE.REAL, "存货周转率");
        Table_Item Fina_Indicator_ar_turn_out = new Table_Item("ar_turn", SQLITE_TYPE.REAL, "应收账款周转率");
        Table_Item Fina_Indicator_ca_turn_out = new Table_Item("ca_turn", SQLITE_TYPE.REAL, "流动资产周转率");
        Table_Item Fina_Indicator_fa_turn_out = new Table_Item("fa_turn", SQLITE_TYPE.REAL, "固定资产周转率");
        Table_Item Fina_Indicator_assets_turn_out = new Table_Item("assets_turn", SQLITE_TYPE.REAL, "总资产周转率");
        Table_Item Fina_Indicator_op_income_out = new Table_Item("op_income", SQLITE_TYPE.REAL, "经营活动净收益");
        Table_Item Fina_Indicator_valuechange_income_out = new Table_Item("valuechange_income", SQLITE_TYPE.REAL, "价值变动净收益");
        Table_Item Fina_Indicator_interst_income_out = new Table_Item("interst_income", SQLITE_TYPE.REAL, "利息费用");
        Table_Item Fina_Indicator_daa_out = new Table_Item("daa", SQLITE_TYPE.REAL, "折旧与摊销");
        Table_Item Fina_Indicator_ebit_out = new Table_Item("ebit", SQLITE_TYPE.REAL, "息税前利润");
        Table_Item Fina_Indicator_ebitda_out = new Table_Item("ebitda", SQLITE_TYPE.REAL, "息税折旧摊销前利润");
        Table_Item Fina_Indicator_fcff_out = new Table_Item("fcff", SQLITE_TYPE.REAL, "企业自由现金流量");
        Table_Item Fina_Indicator_fcfe_out = new Table_Item("fcfe", SQLITE_TYPE.REAL, "股权自由现金流量");
        Table_Item Fina_Indicator_current_exint_out = new Table_Item("current_exint", SQLITE_TYPE.REAL, "无息流动负债");
        Table_Item Fina_Indicator_noncurrent_exint_out = new Table_Item("noncurrent_exint", SQLITE_TYPE.REAL, "无息非流动负债");
        Table_Item Fina_Indicator_interestdebt_out = new Table_Item("interestdebt", SQLITE_TYPE.REAL, "带息债务");
        Table_Item Fina_Indicator_netdebt_out = new Table_Item("netdebt", SQLITE_TYPE.REAL, "净债务");
        Table_Item Fina_Indicator_tangible_asset_out = new Table_Item("tangible_asset", SQLITE_TYPE.REAL, "有形资产");
        Table_Item Fina_Indicator_working_capital_out = new Table_Item("working_capital", SQLITE_TYPE.REAL, "营运资金");
        Table_Item Fina_Indicator_networking_capital_out = new Table_Item("networking_capital", SQLITE_TYPE.REAL, "营运流动资本");
        Table_Item Fina_Indicator_invest_capital_out = new Table_Item("invest_capital", SQLITE_TYPE.REAL, "全部投入资本");
        Table_Item Fina_Indicator_retained_earnings_out = new Table_Item("retained_earnings", SQLITE_TYPE.REAL, "留存收益");
        Table_Item Fina_Indicator_diluted2_eps_out = new Table_Item("diluted2_eps", SQLITE_TYPE.REAL, "期末摊薄每股收益");
        Table_Item Fina_Indicator_bps_out = new Table_Item("bps", SQLITE_TYPE.REAL, "每股净资产");
        Table_Item Fina_Indicator_ocfps_out = new Table_Item("ocfps", SQLITE_TYPE.REAL, "每股经营活动产生的现金流量净额");
        Table_Item Fina_Indicator_retainedps_out = new Table_Item("retainedps", SQLITE_TYPE.REAL, "每股留存收益");
        Table_Item Fina_Indicator_cfps_out = new Table_Item("cfps", SQLITE_TYPE.REAL, "每股现金流量净额");
        Table_Item Fina_Indicator_ebit_ps_out = new Table_Item("ebit_ps", SQLITE_TYPE.REAL, "每股息税前利润");
        Table_Item Fina_Indicator_fcff_ps_out = new Table_Item("fcff_ps", SQLITE_TYPE.REAL, "每股企业自由现金流量");
        Table_Item Fina_Indicator_fcfe_ps_out = new Table_Item("fcfe_ps", SQLITE_TYPE.REAL, "每股股东自由现金流量");
        Table_Item Fina_Indicator_netprofit_margin_out = new Table_Item("netprofit_margin", SQLITE_TYPE.REAL, "销售净利率");
        Table_Item Fina_Indicator_grossprofit_margin_out = new Table_Item("grossprofit_margin", SQLITE_TYPE.REAL, "销售毛利率");
        Table_Item Fina_Indicator_cogs_of_sales_out = new Table_Item("cogs_of_sales", SQLITE_TYPE.REAL, "销售成本率");
        Table_Item Fina_Indicator_expense_of_sales_out = new Table_Item("expense_of_sales", SQLITE_TYPE.REAL, "销售期间费用率");
        Table_Item Fina_Indicator_profit_to_gr_out = new Table_Item("profit_to_gr", SQLITE_TYPE.REAL, "净利润营业总收入");
        Table_Item Fina_Indicator_saleexp_to_gr_out = new Table_Item("saleexp_to_gr", SQLITE_TYPE.REAL, "销售费用营业总收入");
        Table_Item Fina_Indicator_adminexp_of_gr_out = new Table_Item("adminexp_of_gr", SQLITE_TYPE.REAL, "管理费用营业总收入");
        Table_Item Fina_Indicator_finaexp_of_gr_out = new Table_Item("finaexp_of_gr", SQLITE_TYPE.REAL, "财务费用营业总收入");
        Table_Item Fina_Indicator_impai_ttm_out = new Table_Item("impai_ttm", SQLITE_TYPE.REAL, "资产减值损失营业总收入");
        Table_Item Fina_Indicator_gc_of_gr_out = new Table_Item("gc_of_gr", SQLITE_TYPE.REAL, "营业总成本营业总收入");
        Table_Item Fina_Indicator_op_of_gr_out = new Table_Item("op_of_gr", SQLITE_TYPE.REAL, "营业利润营业总收入");
        Table_Item Fina_Indicator_ebit_of_gr_out = new Table_Item("ebit_of_gr", SQLITE_TYPE.REAL, "息税前利润营业总收入");
        Table_Item Fina_Indicator_roe_out = new Table_Item("roe", SQLITE_TYPE.REAL, "净资产收益率");
        Table_Item Fina_Indicator_roe_waa_out = new Table_Item("roe_waa", SQLITE_TYPE.REAL, "加权平均净资产收益率");
        Table_Item Fina_Indicator_roe_dt_out = new Table_Item("roe_dt", SQLITE_TYPE.REAL, "净资产收益率扣除非经常损益");
        Table_Item Fina_Indicator_roa_out = new Table_Item("roa", SQLITE_TYPE.REAL, "总资产报酬率");
        Table_Item Fina_Indicator_npta_out = new Table_Item("npta", SQLITE_TYPE.REAL, "总资产净利润");
        Table_Item Fina_Indicator_roic_out = new Table_Item("roic", SQLITE_TYPE.REAL, "投入资本回报率");
        Table_Item Fina_Indicator_roe_yearly_out = new Table_Item("roe_yearly", SQLITE_TYPE.REAL, "年化净资产收益率");
        Table_Item Fina_Indicator_roa2_yearly_out = new Table_Item("roa2_yearly", SQLITE_TYPE.REAL, "年化总资产报酬率");
        Table_Item Fina_Indicator_roe_avg_out = new Table_Item("roe_avg", SQLITE_TYPE.REAL, "平均净资产收益率增发条件");
        Table_Item Fina_Indicator_opincome_of_ebt_out = new Table_Item("opincome_of_ebt", SQLITE_TYPE.REAL, "经营活动净收益利润总额");
        Table_Item Fina_Indicator_investincome_of_ebt_out = new Table_Item("investincome_of_ebt", SQLITE_TYPE.REAL, "价值变动净收益利润总额");
        Table_Item Fina_Indicator_n_op_profit_of_ebt_out = new Table_Item("n_op_profit_of_ebt", SQLITE_TYPE.REAL, "营业外收支净额利润总额");
        Table_Item Fina_Indicator_tax_to_ebt_out = new Table_Item("tax_to_ebt", SQLITE_TYPE.REAL, "所得税利润总额");
        Table_Item Fina_Indicator_dtprofit_to_profit_out = new Table_Item("dtprofit_to_profit", SQLITE_TYPE.REAL, "扣除非经常损益后的净利润净利润");
        Table_Item Fina_Indicator_salescash_to_or_out = new Table_Item("salescash_to_or", SQLITE_TYPE.REAL, "销售商品提供劳务收到的现金营业收入");
        Table_Item Fina_Indicator_ocf_to_or_out = new Table_Item("ocf_to_or", SQLITE_TYPE.REAL, "经营活动产生的现金流量净额营业收入");
        Table_Item Fina_Indicator_ocf_to_opincome_out = new Table_Item("ocf_to_opincome", SQLITE_TYPE.REAL, "经营活动产生的现金流量净额经营活动净收益");
        Table_Item Fina_Indicator_capitalized_to_da_out = new Table_Item("capitalized_to_da", SQLITE_TYPE.REAL, "资本支出折旧和摊销");
        Table_Item Fina_Indicator_debt_to_assets_out = new Table_Item("debt_to_assets", SQLITE_TYPE.REAL, "资产负债率");
        Table_Item Fina_Indicator_assets_to_eqt_out = new Table_Item("assets_to_eqt", SQLITE_TYPE.REAL, "权益乘数");
        Table_Item Fina_Indicator_dp_assets_to_eqt_out = new Table_Item("dp_assets_to_eqt", SQLITE_TYPE.REAL, "权益乘数杜邦分析");
        Table_Item Fina_Indicator_ca_to_assets_out = new Table_Item("ca_to_assets", SQLITE_TYPE.REAL, "流动资产总资产");
        Table_Item Fina_Indicator_nca_to_assets_out = new Table_Item("nca_to_assets", SQLITE_TYPE.REAL, "非流动资产总资产");
        Table_Item Fina_Indicator_tbassets_to_totalassets_out = new Table_Item("tbassets_to_totalassets", SQLITE_TYPE.REAL, "有形资产总资产");
        Table_Item Fina_Indicator_int_to_talcap_out = new Table_Item("int_to_talcap", SQLITE_TYPE.REAL, "带息债务全部投入资本");
        Table_Item Fina_Indicator_eqt_to_talcapital_out = new Table_Item("eqt_to_talcapital", SQLITE_TYPE.REAL, "归属于母公司的股东权益全部投入资本");
        Table_Item Fina_Indicator_currentdebt_to_debt_out = new Table_Item("currentdebt_to_debt", SQLITE_TYPE.REAL, "流动负债负债合计");
        Table_Item Fina_Indicator_longdeb_to_debt_out = new Table_Item("longdeb_to_debt", SQLITE_TYPE.REAL, "非流动负债负债合计");
        Table_Item Fina_Indicator_ocf_to_shortdebt_out = new Table_Item("ocf_to_shortdebt", SQLITE_TYPE.REAL, "经营活动产生的现金流量净额流动负债");
        Table_Item Fina_Indicator_debt_to_eqt_out = new Table_Item("debt_to_eqt", SQLITE_TYPE.REAL, "产权比率");
        Table_Item Fina_Indicator_eqt_to_debt_out = new Table_Item("eqt_to_debt", SQLITE_TYPE.REAL, "归属于母公司的股东权益负债合计");
        Table_Item Fina_Indicator_eqt_to_interestdebt_out = new Table_Item("eqt_to_interestdebt", SQLITE_TYPE.REAL, "归属于母公司的股东权益带息债务");
        Table_Item Fina_Indicator_tangibleasset_to_debt_out = new Table_Item("tangibleasset_to_debt", SQLITE_TYPE.REAL, "有形资产负债合计");
        Table_Item Fina_Indicator_tangasset_to_intdebt_out = new Table_Item("tangasset_to_intdebt", SQLITE_TYPE.REAL, "有形资产带息债务");
        Table_Item Fina_Indicator_tangibleasset_to_netdebt_out = new Table_Item("tangibleasset_to_netdebt", SQLITE_TYPE.REAL, "有形资产净债务");
        Table_Item Fina_Indicator_ocf_to_debt_out = new Table_Item("ocf_to_debt", SQLITE_TYPE.REAL, "经营活动产生的现金流量净额负债合计");
        Table_Item Fina_Indicator_ocf_to_interestdebt_out = new Table_Item("ocf_to_interestdebt", SQLITE_TYPE.REAL, "经营活动产生的现金流量净额带息债务");
        Table_Item Fina_Indicator_ocf_to_netdebt_out = new Table_Item("ocf_to_netdebt", SQLITE_TYPE.REAL, "经营活动产生的现金流量净额净债务");
        Table_Item Fina_Indicator_ebit_to_interest_out = new Table_Item("ebit_to_interest", SQLITE_TYPE.REAL, "已获利息倍数利息费用");
        Table_Item Fina_Indicator_longdebt_to_workingcapital_out = new Table_Item("longdebt_to_workingcapital", SQLITE_TYPE.REAL, "长期债务与营运资金比率");
        Table_Item Fina_Indicator_ebitda_to_debt_out = new Table_Item("ebitda_to_debt", SQLITE_TYPE.REAL, "息税折旧摊销前利润负债合计");
        Table_Item Fina_Indicator_turn_days_out = new Table_Item("turn_days", SQLITE_TYPE.REAL, "营业周期");
        Table_Item Fina_Indicator_roa_yearly_out = new Table_Item("roa_yearly", SQLITE_TYPE.REAL, "年化总资产净利率");
        Table_Item Fina_Indicator_roa_dp_out = new Table_Item("roa_dp", SQLITE_TYPE.REAL, "总资产净利率杜邦分析");
        Table_Item Fina_Indicator_fixed_assets_out = new Table_Item("fixed_assets", SQLITE_TYPE.REAL, "固定资产合计");
        Table_Item Fina_Indicator_profit_prefin_exp_out = new Table_Item("profit_prefin_exp", SQLITE_TYPE.REAL, "扣除财务费用前营业利润");
        Table_Item Fina_Indicator_non_op_profit_out = new Table_Item("non_op_profit", SQLITE_TYPE.REAL, "非营业利润");
        Table_Item Fina_Indicator_op_to_ebt_out = new Table_Item("op_to_ebt", SQLITE_TYPE.REAL, "营业利润利润总额");
        Table_Item Fina_Indicator_nop_to_ebt_out = new Table_Item("nop_to_ebt", SQLITE_TYPE.REAL, "非营业利润利润总额");
        Table_Item Fina_Indicator_ocf_to_profit_out = new Table_Item("ocf_to_profit", SQLITE_TYPE.REAL, "经营活动产生的现金流量净额营业利润");
        Table_Item Fina_Indicator_cash_to_liqdebt_out = new Table_Item("cash_to_liqdebt", SQLITE_TYPE.REAL, "货币资金流动负债");
        Table_Item Fina_Indicator_cash_to_liqdebt_withinterest_out = new Table_Item("cash_to_liqdebt_withinterest", SQLITE_TYPE.REAL, "货币资金带息流动负债");
        Table_Item Fina_Indicator_op_to_liqdebt_out = new Table_Item("op_to_liqdebt", SQLITE_TYPE.REAL, "营业利润流动负债");
        Table_Item Fina_Indicator_op_to_debt_out = new Table_Item("op_to_debt", SQLITE_TYPE.REAL, "营业利润负债合计");
        Table_Item Fina_Indicator_roic_yearly_out = new Table_Item("roic_yearly", SQLITE_TYPE.REAL, "年化投入资本回报率");
        Table_Item Fina_Indicator_total_fa_trun_out = new Table_Item("total_fa_trun", SQLITE_TYPE.REAL, "固定资产合计周转率");
        Table_Item Fina_Indicator_profit_to_op_out = new Table_Item("profit_to_op", SQLITE_TYPE.REAL, "利润总额营业收入");
        Table_Item Fina_Indicator_q_opincome_out = new Table_Item("q_opincome", SQLITE_TYPE.REAL, "经营活动单季度净收益");
        Table_Item Fina_Indicator_q_investincome_out = new Table_Item("q_investincome", SQLITE_TYPE.REAL, "价值变动单季度净收益");
        Table_Item Fina_Indicator_q_dtprofit_out = new Table_Item("q_dtprofit", SQLITE_TYPE.REAL, "扣除非经常损益后的单季度净利润");
        Table_Item Fina_Indicator_q_eps_out = new Table_Item("q_eps", SQLITE_TYPE.REAL, "每股收益单季度");
        Table_Item Fina_Indicator_q_netprofit_margin_out = new Table_Item("q_netprofit_margin", SQLITE_TYPE.REAL, "销售净利率单季度");
        Table_Item Fina_Indicator_q_gsprofit_margin_out = new Table_Item("q_gsprofit_margin", SQLITE_TYPE.REAL, "销售毛利率单季度");
        Table_Item Fina_Indicator_q_exp_to_sales_out = new Table_Item("q_exp_to_sales", SQLITE_TYPE.REAL, "销售期间费用率单季度");
        Table_Item Fina_Indicator_q_profit_to_gr_out = new Table_Item("q_profit_to_gr", SQLITE_TYPE.REAL, "净利润营业总收入单季度");
        Table_Item Fina_Indicator_q_saleexp_to_gr_out = new Table_Item("q_saleexp_to_gr", SQLITE_TYPE.REAL, "销售费用营业总收入单季度");
        Table_Item Fina_Indicator_q_adminexp_to_gr_out = new Table_Item("q_adminexp_to_gr", SQLITE_TYPE.REAL, "管理费用营业总收入单季度");
        Table_Item Fina_Indicator_q_finaexp_to_gr_out = new Table_Item("q_finaexp_to_gr", SQLITE_TYPE.REAL, "财务费用营业总收入单季度");
        Table_Item Fina_Indicator_q_impair_to_gr_ttm_out = new Table_Item("q_impair_to_gr_ttm", SQLITE_TYPE.REAL, "资产减值损失营业总收入单季度");
        Table_Item Fina_Indicator_q_gc_to_gr_out = new Table_Item("q_gc_to_gr", SQLITE_TYPE.REAL, "营业总成本营业总收入单季度");
        Table_Item Fina_Indicator_q_op_to_gr_out = new Table_Item("q_op_to_gr", SQLITE_TYPE.REAL, "营业利润营业总收入单季度");
        Table_Item Fina_Indicator_q_roe_out = new Table_Item("q_roe", SQLITE_TYPE.REAL, "净资产收益率单季度");
        Table_Item Fina_Indicator_q_dt_roe_out = new Table_Item("q_dt_roe", SQLITE_TYPE.REAL, "净资产单季度收益率扣除非经常损益");
        Table_Item Fina_Indicator_q_npta_out = new Table_Item("q_npta", SQLITE_TYPE.REAL, "总资产净利润单季度");
        Table_Item Fina_Indicator_q_opincome_to_ebt_out = new Table_Item("q_opincome_to_ebt", SQLITE_TYPE.REAL, "经营活动净收益利润总额单季度");
        Table_Item Fina_Indicator_q_investincome_to_ebt_out = new Table_Item("q_investincome_to_ebt", SQLITE_TYPE.REAL, "价值变动净收益利润总额单季度");
        Table_Item Fina_Indicator_q_dtprofit_to_profit_out = new Table_Item("q_dtprofit_to_profit", SQLITE_TYPE.REAL, "扣除非经常损益后的净利润净利润单季度");
        Table_Item Fina_Indicator_q_salescash_to_or_out = new Table_Item("q_salescash_to_or", SQLITE_TYPE.REAL, "销售商品提供劳务收到的现金营业收入单季度");
        Table_Item Fina_Indicator_q_ocf_to_sales_out = new Table_Item("q_ocf_to_sales", SQLITE_TYPE.REAL, "经营活动产生的现金流量净额营业收入单季度");
        Table_Item Fina_Indicator_q_ocf_to_or_out = new Table_Item("q_ocf_to_or", SQLITE_TYPE.REAL, "经营活动产生的现金流量净额经营活动净收益单季度");
        Table_Item Fina_Indicator_basic_eps_yoy_out = new Table_Item("basic_eps_yoy", SQLITE_TYPE.REAL, "基本每股收益同比增长率");
        Table_Item Fina_Indicator_dt_eps_yoy_out = new Table_Item("dt_eps_yoy", SQLITE_TYPE.REAL, "稀释每股收益同比增长率");
        Table_Item Fina_Indicator_cfps_yoy_out = new Table_Item("cfps_yoy", SQLITE_TYPE.REAL, "每股经营活动产生的现金流量净额同比增长率");
        Table_Item Fina_Indicator_op_yoy_out = new Table_Item("op_yoy", SQLITE_TYPE.REAL, "营业利润同比增长率");
        Table_Item Fina_Indicator_ebt_yoy_out = new Table_Item("ebt_yoy", SQLITE_TYPE.REAL, "利润总额同比增长率");
        Table_Item Fina_Indicator_netprofit_yoy_out = new Table_Item("netprofit_yoy", SQLITE_TYPE.REAL, "归属母公司股东的净利润同比增长率");
        Table_Item Fina_Indicator_dt_netprofit_yoy_out = new Table_Item("dt_netprofit_yoy", SQLITE_TYPE.REAL, "归属母公司股东的净利润扣除非经常损益同比增长率");
        Table_Item Fina_Indicator_ocf_yoy_out = new Table_Item("ocf_yoy", SQLITE_TYPE.REAL, "经营活动产生的现金流量净额同比增长率");
        Table_Item Fina_Indicator_roe_yoy_out = new Table_Item("roe_yoy", SQLITE_TYPE.REAL, "净资产收益率摊薄同比增长率");
        Table_Item Fina_Indicator_bps_yoy_out = new Table_Item("bps_yoy", SQLITE_TYPE.REAL, "每股净资产相对年初增长率");
        Table_Item Fina_Indicator_assets_yoy_out = new Table_Item("assets_yoy", SQLITE_TYPE.REAL, "资产总计相对年初增长率");
        Table_Item Fina_Indicator_eqt_yoy_out = new Table_Item("eqt_yoy", SQLITE_TYPE.REAL, "归属母公司的股东权益相对年初增长率");
        Table_Item Fina_Indicator_tr_yoy_out = new Table_Item("tr_yoy", SQLITE_TYPE.REAL, "营业总收入同比增长率");
        Table_Item Fina_Indicator_or_yoy_out = new Table_Item("or_yoy", SQLITE_TYPE.REAL, "营业收入同比增长率");
        Table_Item Fina_Indicator_q_gr_yoy_out = new Table_Item("q_gr_yoy", SQLITE_TYPE.REAL, "营业总收入同比增长率单季度");
        Table_Item Fina_Indicator_q_gr_qoq_out = new Table_Item("q_gr_qoq", SQLITE_TYPE.REAL, "营业总收入环比增长率单季度");
        Table_Item Fina_Indicator_q_sales_yoy_out = new Table_Item("q_sales_yoy", SQLITE_TYPE.REAL, "营业收入同比增长率单季度");
        Table_Item Fina_Indicator_q_sales_qoq_out = new Table_Item("q_sales_qoq", SQLITE_TYPE.REAL, "营业收入环比增长率单季度");
        Table_Item Fina_Indicator_q_op_yoy_out = new Table_Item("q_op_yoy", SQLITE_TYPE.REAL, "营业利润同比增长率单季度");
        Table_Item Fina_Indicator_q_op_qoq_out = new Table_Item("q_op_qoq", SQLITE_TYPE.REAL, "营业利润环比增长率单季度");
        Table_Item Fina_Indicator_q_profit_yoy_out = new Table_Item("q_profit_yoy", SQLITE_TYPE.REAL, "净利润同比增长率单季度");
        Table_Item Fina_Indicator_q_profit_qoq_out = new Table_Item("q_profit_qoq", SQLITE_TYPE.REAL, "净利润环比增长率单季度");
        Table_Item Fina_Indicator_q_netprofit_yoy_out = new Table_Item("q_netprofit_yoy", SQLITE_TYPE.REAL, "归属母公司股东的净利润同比增长率单季度");
        Table_Item Fina_Indicator_q_netprofit_qoq_out = new Table_Item("q_netprofit_qoq", SQLITE_TYPE.REAL, "归属母公司股东的净利润环比增长率单季度");
        Table_Item Fina_Indicator_equity_yoy_out = new Table_Item("equity_yoy", SQLITE_TYPE.REAL, "净资产同比增长率");
        Table_Item Fina_Indicator_rd_exp_out = new Table_Item("rd_exp", SQLITE_TYPE.REAL, "研发费用");
        Table_Item Fina_Indicator_update_flag_out = new Table_Item("update_flag", SQLITE_TYPE.TEXT, "更新标识");
        Fina_Indicator.addTableItem(Fina_Indicator_ts_code_out);
        Fina_Indicator.addTableItem(Fina_Indicator_ann_date_out);
        Fina_Indicator.addTableItem(Fina_Indicator_end_date_out);
        Fina_Indicator.addTableItem(Fina_Indicator_eps_out);
        Fina_Indicator.addTableItem(Fina_Indicator_dt_eps_out);
        Fina_Indicator.addTableItem(Fina_Indicator_total_revenue_ps_out);
        Fina_Indicator.addTableItem(Fina_Indicator_revenue_ps_out);
        Fina_Indicator.addTableItem(Fina_Indicator_capital_rese_ps_out);
        Fina_Indicator.addTableItem(Fina_Indicator_surplus_rese_ps_out);
        Fina_Indicator.addTableItem(Fina_Indicator_undist_profit_ps_out);
        Fina_Indicator.addTableItem(Fina_Indicator_extra_item_out);
        Fina_Indicator.addTableItem(Fina_Indicator_profit_dedt_out);
        Fina_Indicator.addTableItem(Fina_Indicator_gross_margin_out);
        Fina_Indicator.addTableItem(Fina_Indicator_current_ratio_out);
        Fina_Indicator.addTableItem(Fina_Indicator_quick_ratio_out);
        Fina_Indicator.addTableItem(Fina_Indicator_cash_ratio_out);
        Fina_Indicator.addTableItem(Fina_Indicator_invturn_days_out);
        Fina_Indicator.addTableItem(Fina_Indicator_arturn_days_out);
        Fina_Indicator.addTableItem(Fina_Indicator_inv_turn_out);
        Fina_Indicator.addTableItem(Fina_Indicator_ar_turn_out);
        Fina_Indicator.addTableItem(Fina_Indicator_ca_turn_out);
        Fina_Indicator.addTableItem(Fina_Indicator_fa_turn_out);
        Fina_Indicator.addTableItem(Fina_Indicator_assets_turn_out);
        Fina_Indicator.addTableItem(Fina_Indicator_op_income_out);
        Fina_Indicator.addTableItem(Fina_Indicator_valuechange_income_out);
        Fina_Indicator.addTableItem(Fina_Indicator_interst_income_out);
        Fina_Indicator.addTableItem(Fina_Indicator_daa_out);
        Fina_Indicator.addTableItem(Fina_Indicator_ebit_out);
        Fina_Indicator.addTableItem(Fina_Indicator_ebitda_out);
        Fina_Indicator.addTableItem(Fina_Indicator_fcff_out);
        Fina_Indicator.addTableItem(Fina_Indicator_fcfe_out);
        Fina_Indicator.addTableItem(Fina_Indicator_current_exint_out);
        Fina_Indicator.addTableItem(Fina_Indicator_noncurrent_exint_out);
        Fina_Indicator.addTableItem(Fina_Indicator_interestdebt_out);
        Fina_Indicator.addTableItem(Fina_Indicator_netdebt_out);
        Fina_Indicator.addTableItem(Fina_Indicator_tangible_asset_out);
        Fina_Indicator.addTableItem(Fina_Indicator_working_capital_out);
        Fina_Indicator.addTableItem(Fina_Indicator_networking_capital_out);
        Fina_Indicator.addTableItem(Fina_Indicator_invest_capital_out);
        Fina_Indicator.addTableItem(Fina_Indicator_retained_earnings_out);
        Fina_Indicator.addTableItem(Fina_Indicator_diluted2_eps_out);
        Fina_Indicator.addTableItem(Fina_Indicator_bps_out);
        Fina_Indicator.addTableItem(Fina_Indicator_ocfps_out);
        Fina_Indicator.addTableItem(Fina_Indicator_retainedps_out);
        Fina_Indicator.addTableItem(Fina_Indicator_cfps_out);
        Fina_Indicator.addTableItem(Fina_Indicator_ebit_ps_out);
        Fina_Indicator.addTableItem(Fina_Indicator_fcff_ps_out);
        Fina_Indicator.addTableItem(Fina_Indicator_fcfe_ps_out);
        Fina_Indicator.addTableItem(Fina_Indicator_netprofit_margin_out);
        Fina_Indicator.addTableItem(Fina_Indicator_grossprofit_margin_out);
        Fina_Indicator.addTableItem(Fina_Indicator_cogs_of_sales_out);
        Fina_Indicator.addTableItem(Fina_Indicator_expense_of_sales_out);
        Fina_Indicator.addTableItem(Fina_Indicator_profit_to_gr_out);
        Fina_Indicator.addTableItem(Fina_Indicator_saleexp_to_gr_out);
        Fina_Indicator.addTableItem(Fina_Indicator_adminexp_of_gr_out);
        Fina_Indicator.addTableItem(Fina_Indicator_finaexp_of_gr_out);
        Fina_Indicator.addTableItem(Fina_Indicator_impai_ttm_out);
        Fina_Indicator.addTableItem(Fina_Indicator_gc_of_gr_out);
        Fina_Indicator.addTableItem(Fina_Indicator_op_of_gr_out);
        Fina_Indicator.addTableItem(Fina_Indicator_ebit_of_gr_out);
        Fina_Indicator.addTableItem(Fina_Indicator_roe_out);
        Fina_Indicator.addTableItem(Fina_Indicator_roe_waa_out);
        Fina_Indicator.addTableItem(Fina_Indicator_roe_dt_out);
        Fina_Indicator.addTableItem(Fina_Indicator_roa_out);
        Fina_Indicator.addTableItem(Fina_Indicator_npta_out);
        Fina_Indicator.addTableItem(Fina_Indicator_roic_out);
        Fina_Indicator.addTableItem(Fina_Indicator_roe_yearly_out);
        Fina_Indicator.addTableItem(Fina_Indicator_roa2_yearly_out);
        Fina_Indicator.addTableItem(Fina_Indicator_roe_avg_out);
        Fina_Indicator.addTableItem(Fina_Indicator_opincome_of_ebt_out);
        Fina_Indicator.addTableItem(Fina_Indicator_investincome_of_ebt_out);
        Fina_Indicator.addTableItem(Fina_Indicator_n_op_profit_of_ebt_out);
        Fina_Indicator.addTableItem(Fina_Indicator_tax_to_ebt_out);
        Fina_Indicator.addTableItem(Fina_Indicator_dtprofit_to_profit_out);
        Fina_Indicator.addTableItem(Fina_Indicator_salescash_to_or_out);
        Fina_Indicator.addTableItem(Fina_Indicator_ocf_to_or_out);
        Fina_Indicator.addTableItem(Fina_Indicator_ocf_to_opincome_out);
        Fina_Indicator.addTableItem(Fina_Indicator_capitalized_to_da_out);
        Fina_Indicator.addTableItem(Fina_Indicator_debt_to_assets_out);
        Fina_Indicator.addTableItem(Fina_Indicator_assets_to_eqt_out);
        Fina_Indicator.addTableItem(Fina_Indicator_dp_assets_to_eqt_out);
        Fina_Indicator.addTableItem(Fina_Indicator_ca_to_assets_out);
        Fina_Indicator.addTableItem(Fina_Indicator_nca_to_assets_out);
        Fina_Indicator.addTableItem(Fina_Indicator_tbassets_to_totalassets_out);
        Fina_Indicator.addTableItem(Fina_Indicator_int_to_talcap_out);
        Fina_Indicator.addTableItem(Fina_Indicator_eqt_to_talcapital_out);
        Fina_Indicator.addTableItem(Fina_Indicator_currentdebt_to_debt_out);
        Fina_Indicator.addTableItem(Fina_Indicator_longdeb_to_debt_out);
        Fina_Indicator.addTableItem(Fina_Indicator_ocf_to_shortdebt_out);
        Fina_Indicator.addTableItem(Fina_Indicator_debt_to_eqt_out);
        Fina_Indicator.addTableItem(Fina_Indicator_eqt_to_debt_out);
        Fina_Indicator.addTableItem(Fina_Indicator_eqt_to_interestdebt_out);
        Fina_Indicator.addTableItem(Fina_Indicator_tangibleasset_to_debt_out);
        Fina_Indicator.addTableItem(Fina_Indicator_tangasset_to_intdebt_out);
        Fina_Indicator.addTableItem(Fina_Indicator_tangibleasset_to_netdebt_out);
        Fina_Indicator.addTableItem(Fina_Indicator_ocf_to_debt_out);
        Fina_Indicator.addTableItem(Fina_Indicator_ocf_to_interestdebt_out);
        Fina_Indicator.addTableItem(Fina_Indicator_ocf_to_netdebt_out);
        Fina_Indicator.addTableItem(Fina_Indicator_ebit_to_interest_out);
        Fina_Indicator.addTableItem(Fina_Indicator_longdebt_to_workingcapital_out);
        Fina_Indicator.addTableItem(Fina_Indicator_ebitda_to_debt_out);
        Fina_Indicator.addTableItem(Fina_Indicator_turn_days_out);
        Fina_Indicator.addTableItem(Fina_Indicator_roa_yearly_out);
        Fina_Indicator.addTableItem(Fina_Indicator_roa_dp_out);
        Fina_Indicator.addTableItem(Fina_Indicator_fixed_assets_out);
        Fina_Indicator.addTableItem(Fina_Indicator_profit_prefin_exp_out);
        Fina_Indicator.addTableItem(Fina_Indicator_non_op_profit_out);
        Fina_Indicator.addTableItem(Fina_Indicator_op_to_ebt_out);
        Fina_Indicator.addTableItem(Fina_Indicator_nop_to_ebt_out);
        Fina_Indicator.addTableItem(Fina_Indicator_ocf_to_profit_out);
        Fina_Indicator.addTableItem(Fina_Indicator_cash_to_liqdebt_out);
        Fina_Indicator.addTableItem(Fina_Indicator_cash_to_liqdebt_withinterest_out);
        Fina_Indicator.addTableItem(Fina_Indicator_op_to_liqdebt_out);
        Fina_Indicator.addTableItem(Fina_Indicator_op_to_debt_out);
        Fina_Indicator.addTableItem(Fina_Indicator_roic_yearly_out);
        Fina_Indicator.addTableItem(Fina_Indicator_total_fa_trun_out);
        Fina_Indicator.addTableItem(Fina_Indicator_profit_to_op_out);
        Fina_Indicator.addTableItem(Fina_Indicator_q_opincome_out);
        Fina_Indicator.addTableItem(Fina_Indicator_q_investincome_out);
        Fina_Indicator.addTableItem(Fina_Indicator_q_dtprofit_out);
        Fina_Indicator.addTableItem(Fina_Indicator_q_eps_out);
        Fina_Indicator.addTableItem(Fina_Indicator_q_netprofit_margin_out);
        Fina_Indicator.addTableItem(Fina_Indicator_q_gsprofit_margin_out);
        Fina_Indicator.addTableItem(Fina_Indicator_q_exp_to_sales_out);
        Fina_Indicator.addTableItem(Fina_Indicator_q_profit_to_gr_out);
        Fina_Indicator.addTableItem(Fina_Indicator_q_saleexp_to_gr_out);
        Fina_Indicator.addTableItem(Fina_Indicator_q_adminexp_to_gr_out);
        Fina_Indicator.addTableItem(Fina_Indicator_q_finaexp_to_gr_out);
        Fina_Indicator.addTableItem(Fina_Indicator_q_impair_to_gr_ttm_out);
        Fina_Indicator.addTableItem(Fina_Indicator_q_gc_to_gr_out);
        Fina_Indicator.addTableItem(Fina_Indicator_q_op_to_gr_out);
        Fina_Indicator.addTableItem(Fina_Indicator_q_roe_out);
        Fina_Indicator.addTableItem(Fina_Indicator_q_dt_roe_out);
        Fina_Indicator.addTableItem(Fina_Indicator_q_npta_out);
        Fina_Indicator.addTableItem(Fina_Indicator_q_opincome_to_ebt_out);
        Fina_Indicator.addTableItem(Fina_Indicator_q_investincome_to_ebt_out);
        Fina_Indicator.addTableItem(Fina_Indicator_q_dtprofit_to_profit_out);
        Fina_Indicator.addTableItem(Fina_Indicator_q_salescash_to_or_out);
        Fina_Indicator.addTableItem(Fina_Indicator_q_ocf_to_sales_out);
        Fina_Indicator.addTableItem(Fina_Indicator_q_ocf_to_or_out);
        Fina_Indicator.addTableItem(Fina_Indicator_basic_eps_yoy_out);
        Fina_Indicator.addTableItem(Fina_Indicator_dt_eps_yoy_out);
        Fina_Indicator.addTableItem(Fina_Indicator_cfps_yoy_out);
        Fina_Indicator.addTableItem(Fina_Indicator_op_yoy_out);
        Fina_Indicator.addTableItem(Fina_Indicator_ebt_yoy_out);
        Fina_Indicator.addTableItem(Fina_Indicator_netprofit_yoy_out);
        Fina_Indicator.addTableItem(Fina_Indicator_dt_netprofit_yoy_out);
        Fina_Indicator.addTableItem(Fina_Indicator_ocf_yoy_out);
        Fina_Indicator.addTableItem(Fina_Indicator_roe_yoy_out);
        Fina_Indicator.addTableItem(Fina_Indicator_bps_yoy_out);
        Fina_Indicator.addTableItem(Fina_Indicator_assets_yoy_out);
        Fina_Indicator.addTableItem(Fina_Indicator_eqt_yoy_out);
        Fina_Indicator.addTableItem(Fina_Indicator_tr_yoy_out);
        Fina_Indicator.addTableItem(Fina_Indicator_or_yoy_out);
        Fina_Indicator.addTableItem(Fina_Indicator_q_gr_yoy_out);
        Fina_Indicator.addTableItem(Fina_Indicator_q_gr_qoq_out);
        Fina_Indicator.addTableItem(Fina_Indicator_q_sales_yoy_out);
        Fina_Indicator.addTableItem(Fina_Indicator_q_sales_qoq_out);
        Fina_Indicator.addTableItem(Fina_Indicator_q_op_yoy_out);
        Fina_Indicator.addTableItem(Fina_Indicator_q_op_qoq_out);
        Fina_Indicator.addTableItem(Fina_Indicator_q_profit_yoy_out);
        Fina_Indicator.addTableItem(Fina_Indicator_q_profit_qoq_out);
        Fina_Indicator.addTableItem(Fina_Indicator_q_netprofit_yoy_out);
        Fina_Indicator.addTableItem(Fina_Indicator_q_netprofit_qoq_out);
        Fina_Indicator.addTableItem(Fina_Indicator_equity_yoy_out);
        Fina_Indicator.addTableItem(Fina_Indicator_rd_exp_out);
        Fina_Indicator.addTableItem(Fina_Indicator_update_flag_out);

        SQLite_Tables.add(Fina_Indicator);
    }


    static {
//  【 table_30_fina_audit 】 获取上市公司定期财务审计意见数据
        DB_Table Fina_Audit = new DB_Table("fina_audit");
        Fina_Audit.tableIndex = 30;
        Fina_Audit.viceTableIndex = 1;
        Fina_Audit.scoreLimit = 500;
        Fina_Audit.tableDesc = "获取上市公司定期财务审计意见数据";
        Table_Input_Param Fina_Audit_ts_code = new Table_Input_Param("ts_code", "str", "");
        Fina_Audit_ts_code.desc = "股票代码";
        Table_Input_Param Fina_Audit_ann_date = new Table_Input_Param("ann_date", "str", "");
        Fina_Audit_ann_date.desc = "公告日期";
        Table_Input_Param Fina_Audit_start_date = new Table_Input_Param("start_date", "str", "");
        Fina_Audit_start_date.desc = "公告开始日期";
        Table_Input_Param Fina_Audit_end_date = new Table_Input_Param("end_date", "str", "");
        Fina_Audit_end_date.desc = "公告结束日期";
        Table_Input_Param Fina_Audit_period = new Table_Input_Param("period", "str", "");
        Fina_Audit_period.desc = "报告期每个季度最后一天的日期比如表示年报";
        Fina_Audit.addTableInputParam(Fina_Audit_ts_code);
        Fina_Audit.addTableInputParam(Fina_Audit_ann_date);
        Fina_Audit.addTableInputParam(Fina_Audit_start_date);
        Fina_Audit.addTableInputParam(Fina_Audit_end_date);
        Fina_Audit.addTableInputParam(Fina_Audit_period);

        Table_Item Fina_Audit_ts_code_out = new Table_Item("ts_code", SQLITE_TYPE.TEXT, "股票代码");
        Table_Item Fina_Audit_ann_date_out = new Table_Item("ann_date", SQLITE_TYPE.TEXT, "公告日期");
        Table_Item Fina_Audit_end_date_out = new Table_Item("end_date", SQLITE_TYPE.TEXT, "报告期");
        Table_Item Fina_Audit_audit_result_out = new Table_Item("audit_result", SQLITE_TYPE.TEXT, "审计结果");
        Table_Item Fina_Audit_audit_fees_out = new Table_Item("audit_fees", SQLITE_TYPE.REAL, "审计总费用元");
        Table_Item Fina_Audit_audit_agency_out = new Table_Item("audit_agency", SQLITE_TYPE.TEXT, "会计事务所");
        Table_Item Fina_Audit_audit_sign_out = new Table_Item("audit_sign", SQLITE_TYPE.TEXT, "签字会计师");
        Fina_Audit.addTableItem(Fina_Audit_ts_code_out);
        Fina_Audit.addTableItem(Fina_Audit_ann_date_out);
        Fina_Audit.addTableItem(Fina_Audit_end_date_out);
        Fina_Audit.addTableItem(Fina_Audit_audit_result_out);
        Fina_Audit.addTableItem(Fina_Audit_audit_fees_out);
        Fina_Audit.addTableItem(Fina_Audit_audit_agency_out);
        Fina_Audit.addTableItem(Fina_Audit_audit_sign_out);

        SQLite_Tables.add(Fina_Audit);
    }


    static {
//  【 table_31_fina_mainbz 】 获得上市公司主营业务构成，分地区和产品两种方式
        DB_Table Fina_Mainbz = new DB_Table("fina_mainbz");
        Fina_Mainbz.tableIndex = 31;
        Fina_Mainbz.viceTableIndex = 1;
        Fina_Mainbz.scoreLimit = 500;
        Fina_Mainbz.tableDesc = "获得上市公司主营业务构成，分地区和产品两种方式";
        Table_Input_Param Fina_Mainbz_ts_code = new Table_Input_Param("period", "str", "");
        Fina_Mainbz_ts_code.desc = "股票代码";
        Table_Input_Param Fina_Mainbz_period = new Table_Input_Param("period", "str", "");
        Fina_Mainbz_period.desc = "报告期每个季度最后一天的日期比如表示年报";
        Table_Input_Param Fina_Mainbz_type = new Table_Input_Param("type", "str", "");
        Fina_Mainbz_type.desc = "类型按产品按地区请输入大写字母或者";
        Table_Input_Param Fina_Mainbz_start_date = new Table_Input_Param("start_date", "str", "");
        Fina_Mainbz_start_date.desc = "报告期开始日期";
        Table_Input_Param Fina_Mainbz_end_date = new Table_Input_Param("end_date", "str", "");
        Fina_Mainbz_end_date.desc = "报告期结束日期";
        Fina_Mainbz.addTableInputParam(Fina_Mainbz_ts_code);
        Fina_Mainbz.addTableInputParam(Fina_Mainbz_period);
        Fina_Mainbz.addTableInputParam(Fina_Mainbz_type);
        Fina_Mainbz.addTableInputParam(Fina_Mainbz_start_date);
        Fina_Mainbz.addTableInputParam(Fina_Mainbz_end_date);

        Table_Item Fina_Mainbz_ts_code_out = new Table_Item("ts_code", SQLITE_TYPE.TEXT, "代码");
        Table_Item Fina_Mainbz_end_date_out = new Table_Item("end_date", SQLITE_TYPE.TEXT, "报告期");
        Table_Item Fina_Mainbz_bz_item_out = new Table_Item("bz_item", SQLITE_TYPE.TEXT, "主营业务来源");
        Table_Item Fina_Mainbz_bz_sales_out = new Table_Item("bz_sales", SQLITE_TYPE.REAL, "主营业务收入元");
        Table_Item Fina_Mainbz_bz_profit_out = new Table_Item("bz_profit", SQLITE_TYPE.REAL, "主营业务利润元");
        Table_Item Fina_Mainbz_bz_cost_out = new Table_Item("bz_cost", SQLITE_TYPE.REAL, "主营业务成本元");
        Table_Item Fina_Mainbz_curr_type_out = new Table_Item("curr_type", SQLITE_TYPE.TEXT, "货币代码");
        Table_Item Fina_Mainbz_update_flag_out = new Table_Item("update_flag", SQLITE_TYPE.TEXT, "是否更新");
        Fina_Mainbz.addTableItem(Fina_Mainbz_ts_code_out);
        Fina_Mainbz.addTableItem(Fina_Mainbz_end_date_out);
        Fina_Mainbz.addTableItem(Fina_Mainbz_bz_item_out);
        Fina_Mainbz.addTableItem(Fina_Mainbz_bz_sales_out);
        Fina_Mainbz.addTableItem(Fina_Mainbz_bz_profit_out);
        Fina_Mainbz.addTableItem(Fina_Mainbz_bz_cost_out);
        Fina_Mainbz.addTableItem(Fina_Mainbz_curr_type_out);
        Fina_Mainbz.addTableItem(Fina_Mainbz_update_flag_out);

        SQLite_Tables.add(Fina_Mainbz);
    }


    static {
//  【 table_32_disclosure_date 】 获取财报披露计划日期
        DB_Table Disclosure_Date = new DB_Table("disclosure_date");
        Disclosure_Date.tableIndex = 18;
        Disclosure_Date.viceTableIndex = 1;
        Disclosure_Date.scoreLimit = 500;
        Disclosure_Date.tableDesc = "获取财报披露计划日期";
        Table_Input_Param Disclosure_Date_end_date = new Table_Input_Param("end_date", "str", "");
        Disclosure_Date_end_date.desc = "财报周期比如表示年年报表示中报";
        Table_Input_Param Disclosure_Date_pre_date = new Table_Input_Param("pre_date", "str", "");
        Disclosure_Date_pre_date.desc = "计划披露日期";
        Table_Input_Param Disclosure_Date_actual_date = new Table_Input_Param("actual_date", "str", "");
        Disclosure_Date_actual_date.desc = "实际披露日期";
        Disclosure_Date.addTableInputParam(Disclosure_Date_end_date);
        Disclosure_Date.addTableInputParam(Disclosure_Date_pre_date);
        Disclosure_Date.addTableInputParam(Disclosure_Date_actual_date);

        Table_Item Disclosure_Date_ts_code_out = new Table_Item("ts_code", SQLITE_TYPE.TEXT, "代码");
        Table_Item Disclosure_Date_ann_date_out = new Table_Item("ann_date", SQLITE_TYPE.TEXT, "最新披露公告日");
        Table_Item Disclosure_Date_end_date_out = new Table_Item("end_date", SQLITE_TYPE.TEXT, "报告期");
        Table_Item Disclosure_Date_pre_date_out = new Table_Item("pre_date", SQLITE_TYPE.TEXT, "预计披露日期");
        Table_Item Disclosure_Date_actual_date_out = new Table_Item("actual_date", SQLITE_TYPE.TEXT, "实际披露日期");
        Table_Item Disclosure_Date_modify_date_out = new Table_Item("modify_date", SQLITE_TYPE.TEXT, "披露日期修正记录");
        Disclosure_Date.addTableItem(Disclosure_Date_ts_code_out);
        Disclosure_Date.addTableItem(Disclosure_Date_ann_date_out);
        Disclosure_Date.addTableItem(Disclosure_Date_end_date_out);
        Disclosure_Date.addTableItem(Disclosure_Date_pre_date_out);
        Disclosure_Date.addTableItem(Disclosure_Date_actual_date_out);
        Disclosure_Date.addTableItem(Disclosure_Date_modify_date_out);

        SQLite_Tables.add(Disclosure_Date);
    }


    static {
        // 【Table_33】    ggt_top10   港股通十大成交股
        // {"api_name": "ggt_top10", "token": "43acb9a5ddc2cf73c6c4ea54796748f965457ed57daaa736bb778ea2","params": {"trade_date":"20191018","start_date":"","end_date":"","market_type":""},"fields": "trade_date,ts_code,name,close,p_change,rank,market_type,amount,net_amount,sh_amount,sh_net_amount,sh_buy,sh_sell,sz_amount,sz_net_amount,sz_buy,sz_sell"}

        DB_Table Ggt_Top10 = new DB_Table("ggt_top10");
        Ggt_Top10.tableIndex = 33;
        Ggt_Top10.viceTableIndex = 1;


        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();
//        String dateNameNow = df.format(calendar.getTime());
        String dateNameNow = "20191018";


        Table_Input_Param Ggt_Top10_ts_code = new Table_Input_Param("ts_code", "str", "");
        Ggt_Top10_ts_code.desc = "TS代码";

        Table_Input_Param Ggt_Top10_trade_date = new Table_Input_Param("trade_date", "str", dateNameNow);
        Ggt_Top10_trade_date.desc = "交易日期";

        Table_Input_Param Ggt_Top10_start_date = new Table_Input_Param("start_date", "str", "");
        Ggt_Top10_start_date.desc = "开始日期";
        Table_Input_Param Ggt_Top10_end_date = new Table_Input_Param("end_date", "str", "");
        Ggt_Top10_end_date.desc = "结束日期";

        Table_Input_Param Ggt_Top10_market_type = new Table_Input_Param("market_type", "str", "");
        Ggt_Top10_market_type.desc = "市场类型 2：港股通（沪） 4：港股通（深）";


        Ggt_Top10.addTableInputParam(Ggt_Top10_trade_date);
        Ggt_Top10.addTableInputParam(Ggt_Top10_start_date);
        Ggt_Top10.addTableInputParam(Ggt_Top10_end_date);
        Ggt_Top10.addTableInputParam(Ggt_Top10_market_type);


        Table_Item Hk_Hold_out_trade_date = new Table_Item("trade_date	", SQLITE_TYPE.TEXT, "交易日期");
        Table_Item Hk_Hold_out_ts_code = new Table_Item("ts_code	", SQLITE_TYPE.TEXT, "股票代码");
        Table_Item Hk_Hold_out_name = new Table_Item("name	", SQLITE_TYPE.TEXT, "股票名称");
        Table_Item Hk_Hold_out_close = new Table_Item("close	", SQLITE_TYPE.REAL, "收盘价");
        Table_Item Hk_Hold_out_p_change = new Table_Item("p_change	", SQLITE_TYPE.REAL, "涨跌幅");
        Table_Item Hk_Hold_out_rank = new Table_Item("rank	", SQLITE_TYPE.TEXT, "资金排名");
        Table_Item Hk_Hold_out_market_type = new Table_Item("market_type	", SQLITE_TYPE.TEXT, "市场类型 2：港股通（沪） 4：港股通（深）");
        Table_Item Hk_Hold_out_amount = new Table_Item("amount	", SQLITE_TYPE.REAL, "累计成交金额（元）");
        Table_Item Hk_Hold_out_net_amount = new Table_Item("net_amount	", SQLITE_TYPE.REAL, "净买入金额（元）");
        Table_Item Hk_Hold_out_sh_amount = new Table_Item("sh_amount	", SQLITE_TYPE.REAL, "沪市成交金额（元）");
        Table_Item Hk_Hold_out_sh_net_amount = new Table_Item("sh_net_amount	", SQLITE_TYPE.REAL, "沪市净买入金额（元）");
        Table_Item Hk_Hold_out_sh_buy = new Table_Item("sh_buy	", SQLITE_TYPE.REAL, "沪市买入金额（元）");
        Table_Item Hk_Hold_out_sh_sell = new Table_Item("sh_sell	", SQLITE_TYPE.REAL, "沪市卖出金额");
        Table_Item Hk_Hold_out_sz_amount = new Table_Item("sz_amount	", SQLITE_TYPE.REAL, "深市成交金额（元）");
        Table_Item Hk_Hold_out_sz_net_amount = new Table_Item("sz_net_amount	", SQLITE_TYPE.REAL, "深市净买入金额（元）");
        Table_Item Hk_Hold_out_sz_buy = new Table_Item("sz_buy	", SQLITE_TYPE.REAL, "深市买入金额（元）");
        Table_Item Hk_Hold_out_sz_sell = new Table_Item("sz_sell	", SQLITE_TYPE.REAL, "深市卖出金额（元）");

        Ggt_Top10.addTableItem(Hk_Hold_out_trade_date);
        Ggt_Top10.addTableItem(Hk_Hold_out_ts_code);
        Ggt_Top10.addTableItem(Hk_Hold_out_name);
        Ggt_Top10.addTableItem(Hk_Hold_out_close);
        Ggt_Top10.addTableItem(Hk_Hold_out_p_change);
        Ggt_Top10.addTableItem(Hk_Hold_out_rank);
        Ggt_Top10.addTableItem(Hk_Hold_out_market_type);
        Ggt_Top10.addTableItem(Hk_Hold_out_amount);
        Ggt_Top10.addTableItem(Hk_Hold_out_net_amount);
        Ggt_Top10.addTableItem(Hk_Hold_out_sh_amount);
        Ggt_Top10.addTableItem(Hk_Hold_out_sh_net_amount);
        Ggt_Top10.addTableItem(Hk_Hold_out_sh_buy);
        Ggt_Top10.addTableItem(Hk_Hold_out_sh_sell);
        Ggt_Top10.addTableItem(Hk_Hold_out_sz_amount);
        Ggt_Top10.addTableItem(Hk_Hold_out_sz_net_amount);
        Ggt_Top10.addTableItem(Hk_Hold_out_sz_buy);
        Ggt_Top10.addTableItem(Hk_Hold_out_sz_sell);

//        SQLite_Tables.add(Ggt_Top10);
    }

    static {
        // 【Table_34】    margin   获取融资融券每日交易汇总数据   没有访问权限    积分 600
        // {"api_name": "margin", "token": "43acb9a5ddc2cf73c6c4ea54796748f965457ed57daaa736bb778ea2","params": {"trade_date":"20191018","exchange_id":"","start_date":"","end_date":""},"fields": "trade_date,exchange_id,rzye,rzmre,rzche,rqye,rqmcl,rzrqye,rqyl"}


        DB_Table Margin = new DB_Table("margin");
        Margin.tableIndex = 34;
        Margin.viceTableIndex = 1;

        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();
//        String dateNameNow = df.format(calendar.getTime());
        String dateNameNow = "20191018";

        Table_Input_Param Margin_trade_date = new Table_Input_Param("trade_date", "str", dateNameNow);
        Margin_trade_date.desc = "交易日期";

        Table_Input_Param Margin_exchange_id = new Table_Input_Param("exchange_id", "str", "");
        Margin_exchange_id.desc = "交易所代码";

        Table_Input_Param Margin_start_date = new Table_Input_Param("start_date", "str", "");
        Margin_start_date.desc = "开始日期";
        Table_Input_Param Margin_end_date = new Table_Input_Param("end_date", "str", "");
        Margin_end_date.desc = "结束日期";


        Margin.addTableInputParam(Margin_trade_date);
        Margin.addTableInputParam(Margin_exchange_id);
        Margin.addTableInputParam(Margin_start_date);
        Margin.addTableInputParam(Margin_end_date);


        Table_Item Hk_Hold_out_trade_date = new Table_Item("trade_date", SQLITE_TYPE.TEXT, "交易日期");
        Table_Item Hk_Hold_out_exchange_id = new Table_Item("exchange_id	", SQLITE_TYPE.TEXT, "交易所代码（SSE上交所SZSE深交所）");
        Table_Item Hk_Hold_out_rzye = new Table_Item("rzye", SQLITE_TYPE.REAL, "融资余额(元)");
        Table_Item Hk_Hold_out_rzmre = new Table_Item("rzmre", SQLITE_TYPE.REAL, "融资买入额(元)");
        Table_Item Hk_Hold_out_rzche = new Table_Item("rzche", SQLITE_TYPE.REAL, "融资偿还额(元)");
        Table_Item Hk_Hold_out_rqye = new Table_Item("rqye", SQLITE_TYPE.REAL, "融券余额(元)");
        Table_Item Hk_Hold_out_rqmcl = new Table_Item("rqmcl", SQLITE_TYPE.REAL, "融券卖出量(股,份,手)");
        Table_Item Hk_Hold_out_rzrqye = new Table_Item("rzrqye", SQLITE_TYPE.REAL, "融资融券余额(元)");
        Table_Item Hk_Hold_out_rqyl = new Table_Item("rqyl", SQLITE_TYPE.REAL, "融券余量(股,份,手)");

        Margin.addTableItem(Hk_Hold_out_trade_date);
        Margin.addTableItem(Hk_Hold_out_exchange_id);
        Margin.addTableItem(Hk_Hold_out_rzye);
        Margin.addTableItem(Hk_Hold_out_rzmre);
        Margin.addTableItem(Hk_Hold_out_rzche);
        Margin.addTableItem(Hk_Hold_out_rqye);
        Margin.addTableItem(Hk_Hold_out_rqmcl);
        Margin.addTableItem(Hk_Hold_out_rzrqye);
        Margin.addTableItem(Hk_Hold_out_rqyl);

//        SQLite_Tables.add(Margin);

    }


    static {
//  【 table_35_margin_detail 】 获取沪深两市每日融资融券明细
        DB_Table Margin_Detail = new DB_Table("margin_detail");
        Margin_Detail.tableIndex = 35;
        Margin_Detail.viceTableIndex = 1;
        Margin_Detail.scoreLimit = 2000;
        Margin_Detail.tableDesc = "获取沪深两市每日融资融券明细";
        Table_Input_Param Margin_Detail_trade_date = new Table_Input_Param("trade_date", "str", "");
        Margin_Detail_trade_date.desc = "交易日期";
        Table_Input_Param Margin_Detail_start_date = new Table_Input_Param("start_date", "str", "");
        Margin_Detail_start_date.desc = "开始日期";
        Table_Input_Param Margin_Detail_end_date = new Table_Input_Param("end_date", "str", "");
        Margin_Detail_end_date.desc = "结束日期";
        Margin_Detail.addTableInputParam(Margin_Detail_trade_date);
        Margin_Detail.addTableInputParam(Margin_Detail_start_date);
        Margin_Detail.addTableInputParam(Margin_Detail_end_date);

        Table_Item Margin_Detail_trade_date_out = new Table_Item("trade_date", SQLITE_TYPE.TEXT, "交易日期");
        Table_Item Margin_Detail_ts_code_out = new Table_Item("ts_code", SQLITE_TYPE.TEXT, "股票代码");
        Table_Item Margin_Detail_name_out = new Table_Item("name", SQLITE_TYPE.TEXT, "股票名称后有数据");
        Table_Item Margin_Detail_rzye_out = new Table_Item("rzye", SQLITE_TYPE.REAL, "融资余额元");
        Table_Item Margin_Detail_rqye_out = new Table_Item("rqye", SQLITE_TYPE.REAL, "融券余额元");
        Table_Item Margin_Detail_rzmre_out = new Table_Item("rzmre", SQLITE_TYPE.REAL, "融资买入额元");
        Table_Item Margin_Detail_rqyl_out = new Table_Item("rqyl", SQLITE_TYPE.REAL, "融券余量手");
        Table_Item Margin_Detail_rzche_out = new Table_Item("rzche", SQLITE_TYPE.REAL, "融资偿还额元");
        Table_Item Margin_Detail_rqchl_out = new Table_Item("rqchl", SQLITE_TYPE.REAL, "融券偿还量手");
        Table_Item Margin_Detail_rqmcl_out = new Table_Item("rqmcl", SQLITE_TYPE.REAL, "融券卖出量股份手");
        Table_Item Margin_Detail_rzrqye_out = new Table_Item("rzrqye", SQLITE_TYPE.REAL, "融资融券余额元");
        Margin_Detail.addTableItem(Margin_Detail_trade_date_out);
        Margin_Detail.addTableItem(Margin_Detail_ts_code_out);
        Margin_Detail.addTableItem(Margin_Detail_name_out);
        Margin_Detail.addTableItem(Margin_Detail_rzye_out);
        Margin_Detail.addTableItem(Margin_Detail_rqye_out);
        Margin_Detail.addTableItem(Margin_Detail_rzmre_out);
        Margin_Detail.addTableItem(Margin_Detail_rqyl_out);
        Margin_Detail.addTableItem(Margin_Detail_rzche_out);
        Margin_Detail.addTableItem(Margin_Detail_rqchl_out);
        Margin_Detail.addTableItem(Margin_Detail_rqmcl_out);
        Margin_Detail.addTableItem(Margin_Detail_rzrqye_out);

        SQLite_Tables.add(Margin_Detail);
    }


    static {
        // 【Table_36_top10_holders】    top10_holders   获取上市公司前十大股东数据，包括持有数量和比例等信息。
        // {"api_name": "top10_holders", "token": "43acb9a5ddc2cf73c6c4ea54796748f965457ed57daaa736bb778ea2","params": {"ts_code":"","period":"","ann_date":"","start_date":"","end_date":""},"fields": "ts_code,ann_date,end_date,holder_name,hold_amount,hold_ratio"}
        // 您每分钟最多访问该接口80次，权限的具体详情访问：


        DB_Table Top10_Holders = new DB_Table("top10_holders");
        Top10_Holders.tableIndex = 15;
        Top10_Holders.viceTableIndex = 1;
        Top10_Holders.tableDesc = "前十大股东数据";

        Table_Input_Param Top10_Holders_ts_code = new Table_Input_Param("ts_code", "str", "");
        Top10_Holders_ts_code.desc = "TS代码";
        Table_Input_Param Top10_Holders_period = new Table_Input_Param("period", "str", "");
        Top10_Holders_period.desc = "报告期";
        Table_Input_Param Top10_Holders_ann_date = new Table_Input_Param("ann_date", "str", "");
        Top10_Holders_ann_date.desc = "公告日期";

        Table_Input_Param Top10_Holders_start_date = new Table_Input_Param("start_date", "str", "");
        Top10_Holders_start_date.desc = "报告期开始日期";

        Table_Input_Param Top10_Holders_end_date = new Table_Input_Param("end_date", "str", "");
        Top10_Holders_end_date.desc = "报告期结束日期";

        Top10_Holders.addTableInputParam(Top10_Holders_ts_code);
        Top10_Holders.addTableInputParam(Top10_Holders_period);
        Top10_Holders.addTableInputParam(Top10_Holders_ann_date);
        Top10_Holders.addTableInputParam(Top10_Holders_start_date);
        Top10_Holders.addTableInputParam(Top10_Holders_end_date);


        Table_Item Top10_Holders_out_ts_code = new Table_Item("ts_code", SQLITE_TYPE.TEXT, "TS股票代码");
        Table_Item Top10_Holders_out_ann_date = new Table_Item("ann_date	", SQLITE_TYPE.TEXT, "公告日期");
        Table_Item Top10_Holders_out_end_date = new Table_Item("end_date	", SQLITE_TYPE.TEXT, "报告期");
        Table_Item Top10_Holders_out_holder_name = new Table_Item("holder_name", SQLITE_TYPE.TEXT, "股东名称");
        Table_Item Top10_Holders_out_hold_amount = new Table_Item("hold_amount", SQLITE_TYPE.REAL, "持有数量（股）");
        Table_Item Top10_Holders_out_hold_ratio = new Table_Item("hold_ratio", SQLITE_TYPE.REAL, "持有比例");

        Top10_Holders.addTableItem(Top10_Holders_out_ts_code);
        Top10_Holders.addTableItem(Top10_Holders_out_ann_date);
        Top10_Holders.addTableItem(Top10_Holders_out_end_date);
        Top10_Holders.addTableItem(Top10_Holders_out_holder_name);
        Top10_Holders.addTableItem(Top10_Holders_out_hold_amount);
        Top10_Holders.addTableItem(Top10_Holders_out_hold_ratio);

//       SQLite_Tables.add(Top10_Holders);

    }


    static {
        // 【Table_37_top10_floatholders】    top10_floatholders   获取上市公司前十大股东数据，包括持有数量和比例等信息。
        // {"api_name": "top10_floatholders", "token": "43acb9a5ddc2cf73c6c4ea54796748f965457ed57daaa736bb778ea2","params": {"ts_code":"","period":"","ann_date":"","start_date":"","end_date":""},"fields": "ts_code,ann_date,end_date,holder_name,hold_amount,hold_ratio"}
        // 您每分钟最多访问该接口80次，权限的具体详情访问：


        DB_Table Top10_Floatholders = new DB_Table("top10_floatholders");
        Top10_Floatholders.tableIndex = 16;
        Top10_Floatholders.viceTableIndex = 1;
        Top10_Floatholders.tableDesc = "前十大流通股股东数据";

        Table_Input_Param Top10_Floatholders_ts_code = new Table_Input_Param("ts_code", "str", "");
        Top10_Floatholders_ts_code.desc = "TS代码";
        Table_Input_Param Top10_Floatholders_period = new Table_Input_Param("period", "str", "");
        Top10_Floatholders_period.desc = "报告期";
        Table_Input_Param Top10_Floatholders_ann_date = new Table_Input_Param("ann_date", "str", "");
        Top10_Floatholders_ann_date.desc = "公告日期";

        Table_Input_Param Top10_Floatholders_start_date = new Table_Input_Param("start_date", "str", "");
        Top10_Floatholders_start_date.desc = "报告期开始日期";

        Table_Input_Param Top10_Floatholders_end_date = new Table_Input_Param("end_date", "str", "");
        Top10_Floatholders_end_date.desc = "报告期结束日期";

        Top10_Floatholders.addTableInputParam(Top10_Floatholders_ts_code);
        Top10_Floatholders.addTableInputParam(Top10_Floatholders_period);
        Top10_Floatholders.addTableInputParam(Top10_Floatholders_ann_date);
        Top10_Floatholders.addTableInputParam(Top10_Floatholders_start_date);
        Top10_Floatholders.addTableInputParam(Top10_Floatholders_end_date);


        Table_Item Top10_Floatholders_out_ts_code = new Table_Item("ts_code", SQLITE_TYPE.TEXT, "TS股票代码");
        Table_Item Top10_Floatholders_out_ann_date = new Table_Item("ann_date", SQLITE_TYPE.TEXT, "公告日期");
        Table_Item Top10_Floatholders_out_end_date = new Table_Item("end_date", SQLITE_TYPE.TEXT, "报告期");
        Table_Item Top10_Floatholders_out_holder_name = new Table_Item("holder_name", SQLITE_TYPE.TEXT, "股东名称");
        Table_Item Top10_Floatholders_out_hold_amount = new Table_Item("hold_amount", SQLITE_TYPE.REAL, "持有数量（股）");

        Top10_Floatholders.addTableItem(Top10_Floatholders_out_ts_code);
        Top10_Floatholders.addTableItem(Top10_Floatholders_out_ann_date);
        Top10_Floatholders.addTableItem(Top10_Floatholders_out_end_date);
        Top10_Floatholders.addTableItem(Top10_Floatholders_out_holder_name);
        Top10_Floatholders.addTableItem(Top10_Floatholders_out_hold_amount);


//       SQLite_Tables.add(Top10_Floatholders);

    }


    static {
//  【 table_38_top_list 】 龙虎榜每日交易明细
        DB_Table Top_List = new DB_Table("top_list");
        Top_List.tableIndex = 38;
        Top_List.viceTableIndex = 1;
        Top_List.scoreLimit = 300;
        Top_List.tableDesc = "龙虎榜每日交易明细";
        Table_Input_Param Top_List_trade_date = new Table_Input_Param("trade_date", "str", "");
        Top_List_trade_date.desc = "交易日期";
        Table_Input_Param Top_List_ts_code = new Table_Input_Param("ts_code", "str", "");
        Top_List_ts_code.desc = "股票代码";
        Top_List.addTableInputParam(Top_List_trade_date);
        Top_List.addTableInputParam(Top_List_ts_code);

        Table_Item Top_List_trade_date_out = new Table_Item("trade_date", SQLITE_TYPE.TEXT, "交易日期");
        Table_Item Top_List_ts_code_out = new Table_Item("ts_code", SQLITE_TYPE.TEXT, "代码");
        Table_Item Top_List_name_out = new Table_Item("name", SQLITE_TYPE.TEXT, "名称");
        Table_Item Top_List_close_out = new Table_Item("close", SQLITE_TYPE.REAL, "收盘价");
        Table_Item Top_List_pct_change_out = new Table_Item("pct_change", SQLITE_TYPE.REAL, "涨跌幅");
        Table_Item Top_List_turnover_rate_out = new Table_Item("turnover_rate", SQLITE_TYPE.REAL, "换手率");
        Table_Item Top_List_amount_out = new Table_Item("amount", SQLITE_TYPE.REAL, "总成交额");
        Table_Item Top_List_l_sell_out = new Table_Item("l_sell", SQLITE_TYPE.REAL, "龙虎榜卖出额");
        Table_Item Top_List_l_buy_out = new Table_Item("l_buy", SQLITE_TYPE.REAL, "龙虎榜买入额");
        Table_Item Top_List_l_amount_out = new Table_Item("l_amount", SQLITE_TYPE.REAL, "龙虎榜成交额");
        Table_Item Top_List_net_amount_out = new Table_Item("net_amount", SQLITE_TYPE.REAL, "龙虎榜净买入额");
        Table_Item Top_List_net_rate_out = new Table_Item("net_rate", SQLITE_TYPE.REAL, "龙虎榜净买额占比");
        Table_Item Top_List_amount_rate_out = new Table_Item("amount_rate", SQLITE_TYPE.REAL, "龙虎榜成交额占比");
        Table_Item Top_List_float_values_out = new Table_Item("float_values", SQLITE_TYPE.REAL, "当日流通市值");
        Table_Item Top_List_reason_out = new Table_Item("reason", SQLITE_TYPE.TEXT, "上榜理由");
        Top_List.addTableItem(Top_List_trade_date_out);
        Top_List.addTableItem(Top_List_ts_code_out);
        Top_List.addTableItem(Top_List_name_out);
        Top_List.addTableItem(Top_List_close_out);
        Top_List.addTableItem(Top_List_pct_change_out);
        Top_List.addTableItem(Top_List_turnover_rate_out);
        Top_List.addTableItem(Top_List_amount_out);
        Top_List.addTableItem(Top_List_l_sell_out);
        Top_List.addTableItem(Top_List_l_buy_out);
        Top_List.addTableItem(Top_List_l_amount_out);
        Top_List.addTableItem(Top_List_net_amount_out);
        Top_List.addTableItem(Top_List_net_rate_out);
        Top_List.addTableItem(Top_List_amount_rate_out);
        Top_List.addTableItem(Top_List_float_values_out);
        Top_List.addTableItem(Top_List_reason_out);

        SQLite_Tables.add(Top_List);
    }


    static {
//  【 table_39_top_inst 】 龙虎榜机构成交明细
        DB_Table Top_Inst = new DB_Table("top_inst");
        Top_Inst.tableIndex = 39;
        Top_Inst.viceTableIndex = 1;
        Top_Inst.scoreLimit = 300;
        Top_Inst.tableDesc = "龙虎榜机构成交明细";
        Table_Input_Param Top_Inst_trade_date = new Table_Input_Param("trade_date", "str", "");
        Top_Inst_trade_date.desc = "交易日期";
        Top_Inst.addTableInputParam(Top_Inst_trade_date);

        Table_Item Top_Inst_trade_date_out = new Table_Item("trade_date", SQLITE_TYPE.TEXT, "交易日期");
        Table_Item Top_Inst_ts_code_out = new Table_Item("ts_code", SQLITE_TYPE.TEXT, "代码");
        Table_Item Top_Inst_exalter_out = new Table_Item("exalter", SQLITE_TYPE.TEXT, "营业部名称");
        Table_Item Top_Inst_buy_out = new Table_Item("buy", SQLITE_TYPE.REAL, "买入额万");
        Table_Item Top_Inst_buy_rate_out = new Table_Item("buy_rate", SQLITE_TYPE.REAL, "买入占总成交比例");
        Table_Item Top_Inst_sell_out = new Table_Item("sell", SQLITE_TYPE.REAL, "卖出额万");
        Table_Item Top_Inst_sell_rate_out = new Table_Item("sell_rate", SQLITE_TYPE.REAL, "卖出占总成交比例");
        Table_Item Top_Inst_net_buy_out = new Table_Item("net_buy", SQLITE_TYPE.REAL, "净成交额万");
        Top_Inst.addTableItem(Top_Inst_trade_date_out);
        Top_Inst.addTableItem(Top_Inst_ts_code_out);
        Top_Inst.addTableItem(Top_Inst_exalter_out);
        Top_Inst.addTableItem(Top_Inst_buy_out);
        Top_Inst.addTableItem(Top_Inst_buy_rate_out);
        Top_Inst.addTableItem(Top_Inst_sell_out);
        Top_Inst.addTableItem(Top_Inst_sell_rate_out);
        Top_Inst.addTableItem(Top_Inst_net_buy_out);

        SQLite_Tables.add(Top_Inst);
    }


    static {
//  【 table_40_pledge_stat 】 获取股权质押统计数据
        DB_Table Pledge_Stat = new DB_Table("pledge_stat");
        Pledge_Stat.tableIndex = 40;
        Pledge_Stat.viceTableIndex = 1;
        Pledge_Stat.scoreLimit = 300;
        Pledge_Stat.tableDesc = "获取股权质押统计数据";
        Table_Input_Param Pledge_Stat_ts_code = new Table_Input_Param("ts_code", "str", "");
        Pledge_Stat_ts_code.desc = "股票代码";
        Pledge_Stat.addTableInputParam(Pledge_Stat_ts_code);

        Table_Item Pledge_Stat_ts_code_out = new Table_Item("ts_code", SQLITE_TYPE.TEXT, "代码");
        Table_Item Pledge_Stat_end_date_out = new Table_Item("end_date", SQLITE_TYPE.TEXT, "截至日期");
        Table_Item Pledge_Stat_pledge_count_out = new Table_Item("pledge_count", SQLITE_TYPE.INT, "质押次数");
        Table_Item Pledge_Stat_unrest_pledge_out = new Table_Item("unrest_pledge", SQLITE_TYPE.REAL, "无限售股质押数量万");
        Table_Item Pledge_Stat_rest_pledge_out = new Table_Item("rest_pledge", SQLITE_TYPE.REAL, "限售股份质押数量万");
        Table_Item Pledge_Stat_total_share_out = new Table_Item("total_share", SQLITE_TYPE.REAL, "总股本");
        Table_Item Pledge_Stat_pledge_ratio_out = new Table_Item("pledge_ratio", SQLITE_TYPE.REAL, "质押比例");
        Pledge_Stat.addTableItem(Pledge_Stat_ts_code_out);
        Pledge_Stat.addTableItem(Pledge_Stat_end_date_out);
        Pledge_Stat.addTableItem(Pledge_Stat_pledge_count_out);
        Pledge_Stat.addTableItem(Pledge_Stat_unrest_pledge_out);
        Pledge_Stat.addTableItem(Pledge_Stat_rest_pledge_out);
        Pledge_Stat.addTableItem(Pledge_Stat_total_share_out);
        Pledge_Stat.addTableItem(Pledge_Stat_pledge_ratio_out);

        SQLite_Tables.add(Pledge_Stat);
    }


    static {
//  【 table_41_pledge_detail 】 获取股权质押明细数据
        DB_Table Pledge_Detail = new DB_Table("pledge_detail");
        Pledge_Detail.tableIndex = 41;
        Pledge_Detail.viceTableIndex = 1;
        Pledge_Detail.scoreLimit = 300;
        Pledge_Detail.tableDesc = "获取股权质押明细数据";
        Table_Input_Param Pledge_Detail_ts_code = new Table_Input_Param("ts_code", "str", "");
        Pledge_Detail_ts_code.desc = "股票代码";
        Pledge_Detail.addTableInputParam(Pledge_Detail_ts_code);

        Table_Item Pledge_Detail_ts_code_out = new Table_Item("ts_code", SQLITE_TYPE.TEXT, "股票代码");
        Table_Item Pledge_Detail_ann_date_out = new Table_Item("ann_date", SQLITE_TYPE.TEXT, "公告日期");
        Table_Item Pledge_Detail_holder_name_out = new Table_Item("holder_name", SQLITE_TYPE.TEXT, "股东名称");
        Table_Item Pledge_Detail_pledge_amount_out = new Table_Item("pledge_amount", SQLITE_TYPE.REAL, "质押数量");
        Table_Item Pledge_Detail_start_date_out = new Table_Item("start_date", SQLITE_TYPE.TEXT, "质押开始日期");
        Table_Item Pledge_Detail_end_date_out = new Table_Item("end_date", SQLITE_TYPE.TEXT, "质押结束日期");
        Table_Item Pledge_Detail_is_release_out = new Table_Item("is_release", SQLITE_TYPE.TEXT, "是否已解押");
        Table_Item Pledge_Detail_release_date_out = new Table_Item("release_date", SQLITE_TYPE.TEXT, "解押日期");
        Table_Item Pledge_Detail_pledgor_out = new Table_Item("pledgor", SQLITE_TYPE.TEXT, "质押方");
        Table_Item Pledge_Detail_holding_amount_out = new Table_Item("holding_amount", SQLITE_TYPE.REAL, "持股总数");
        Table_Item Pledge_Detail_pledged_amount_out = new Table_Item("pledged_amount", SQLITE_TYPE.REAL, "质押总数");
        Table_Item Pledge_Detail_p_total_ratio_out = new Table_Item("p_total_ratio", SQLITE_TYPE.REAL, "本次质押占总股本比例");
        Table_Item Pledge_Detail_h_total_ratio_out = new Table_Item("h_total_ratio", SQLITE_TYPE.REAL, "持股总数占总股本比例");
        Table_Item Pledge_Detail_is_buyback_out = new Table_Item("is_buyback", SQLITE_TYPE.TEXT, "是否回购");
        Pledge_Detail.addTableItem(Pledge_Detail_ts_code_out);
        Pledge_Detail.addTableItem(Pledge_Detail_ann_date_out);
        Pledge_Detail.addTableItem(Pledge_Detail_holder_name_out);
        Pledge_Detail.addTableItem(Pledge_Detail_pledge_amount_out);
        Pledge_Detail.addTableItem(Pledge_Detail_start_date_out);
        Pledge_Detail.addTableItem(Pledge_Detail_end_date_out);
        Pledge_Detail.addTableItem(Pledge_Detail_is_release_out);
        Pledge_Detail.addTableItem(Pledge_Detail_release_date_out);
        Pledge_Detail.addTableItem(Pledge_Detail_pledgor_out);
        Pledge_Detail.addTableItem(Pledge_Detail_holding_amount_out);
        Pledge_Detail.addTableItem(Pledge_Detail_pledged_amount_out);
        Pledge_Detail.addTableItem(Pledge_Detail_p_total_ratio_out);
        Pledge_Detail.addTableItem(Pledge_Detail_h_total_ratio_out);
        Pledge_Detail.addTableItem(Pledge_Detail_is_buyback_out);

        SQLite_Tables.add(Pledge_Detail);
    }


    static {
//  【 table_42_repurchase 】 获取上市公司回购股票数据
        DB_Table Repurchase = new DB_Table("repurchase");
        Repurchase.tableIndex = 42;
        Repurchase.viceTableIndex = 1;
        Repurchase.scoreLimit = 600;
        Repurchase.tableDesc = "获取上市公司回购股票数据";
        Table_Input_Param Repurchase_ann_date = new Table_Input_Param("ann_date", "str", "");
        Repurchase_ann_date.desc = "公告日期任意填参数如果都不填单次默认返回条";
        Table_Input_Param Repurchase_start_date = new Table_Input_Param("start_date", "str", "");
        Repurchase_start_date.desc = "公告开始日期";
        Table_Input_Param Repurchase_end_date = new Table_Input_Param("end_date", "str", "");
        Repurchase_end_date.desc = "公告结束日期";
        Repurchase.addTableInputParam(Repurchase_ann_date);
        Repurchase.addTableInputParam(Repurchase_start_date);
        Repurchase.addTableInputParam(Repurchase_end_date);

        Table_Item Repurchase_ts_code_out = new Table_Item("ts_code", SQLITE_TYPE.TEXT, "代码");
        Table_Item Repurchase_ann_date_out = new Table_Item("ann_date", SQLITE_TYPE.TEXT, "公告日期");
        Table_Item Repurchase_end_date_out = new Table_Item("end_date", SQLITE_TYPE.TEXT, "截止日期");
        Table_Item Repurchase_proc_out = new Table_Item("proc", SQLITE_TYPE.TEXT, "进度");
        Table_Item Repurchase_exp_date_out = new Table_Item("exp_date", SQLITE_TYPE.TEXT, "过期日期");
        Table_Item Repurchase_vol_out = new Table_Item("vol", SQLITE_TYPE.REAL, "回购数量");
        Table_Item Repurchase_amount_out = new Table_Item("amount", SQLITE_TYPE.REAL, "回购金额");
        Table_Item Repurchase_high_limit_out = new Table_Item("high_limit", SQLITE_TYPE.REAL, "回购最高价");
        Table_Item Repurchase_low_limit_out = new Table_Item("low_limit", SQLITE_TYPE.REAL, "回购最低价");
        Repurchase.addTableItem(Repurchase_ts_code_out);
        Repurchase.addTableItem(Repurchase_ann_date_out);
        Repurchase.addTableItem(Repurchase_end_date_out);
        Repurchase.addTableItem(Repurchase_proc_out);
        Repurchase.addTableItem(Repurchase_exp_date_out);
        Repurchase.addTableItem(Repurchase_vol_out);
        Repurchase.addTableItem(Repurchase_amount_out);
        Repurchase.addTableItem(Repurchase_high_limit_out);
        Repurchase.addTableItem(Repurchase_low_limit_out);

        SQLite_Tables.add(Repurchase);
    }


    static {
//  【 table_43_concept 】 获取概念股分类，目前只有ts一个来源，未来将逐步增加来源
        DB_Table Concept = new DB_Table("concept");
        Concept.tableIndex = 43;
        Concept.viceTableIndex = 1;
        Concept.scoreLimit = 300;
        Concept.tableDesc = "获取概念股分类，目前只有ts一个来源，未来将逐步增加来源";
        Table_Input_Param Concept_src = new Table_Input_Param("src", "str", "");
        Concept_src.desc = "来源默认为";
        Concept.addTableInputParam(Concept_src);

        Table_Item Concept_code_out = new Table_Item("code", SQLITE_TYPE.TEXT, "概念分类");
        Table_Item Concept_name_out = new Table_Item("name", SQLITE_TYPE.TEXT, "概念分类名称");
        Table_Item Concept_src_out = new Table_Item("src", SQLITE_TYPE.TEXT, "来源");
        Concept.addTableItem(Concept_code_out);
        Concept.addTableItem(Concept_name_out);
        Concept.addTableItem(Concept_src_out);

        SQLite_Tables.add(Concept);
    }


    static {
//  【 table_44_concept_detail 】 获取概念股分类明细数据
        DB_Table Concept_Detail = new DB_Table("concept_detail");
        Concept_Detail.tableIndex = 44;
        Concept_Detail.viceTableIndex = 1;
        Concept_Detail.scoreLimit = 300;
        Concept_Detail.tableDesc = "获取概念股分类明细数据";
        Table_Input_Param Concept_Detail_id = new Table_Input_Param("id", "str", "");
        Concept_Detail_id.desc = "概念分类来自概念股分类接口";
        Table_Input_Param Concept_Detail_ts_code = new Table_Input_Param("ts_code", "str", "");
        Concept_Detail_ts_code.desc = "股票代码以上参数二选一";
        Concept_Detail.addTableInputParam(Concept_Detail_id);
        Concept_Detail.addTableInputParam(Concept_Detail_ts_code);

        Table_Item Concept_Detail_id_out = new Table_Item("id", SQLITE_TYPE.TEXT, "概念代码");
        Table_Item Concept_Detail_concept_name_out = new Table_Item("concept_name", SQLITE_TYPE.TEXT, "概念名称");
        Table_Item Concept_Detail_ts_code_out = new Table_Item("ts_code", SQLITE_TYPE.TEXT, "股票代码");
        Table_Item Concept_Detail_name_out = new Table_Item("name", SQLITE_TYPE.TEXT, "股票名称");
        Table_Item Concept_Detail_in_date_out = new Table_Item("in_date", SQLITE_TYPE.TEXT, "纳入日期");
        Table_Item Concept_Detail_out_date_out = new Table_Item("out_date", SQLITE_TYPE.TEXT, "剔除日期");
        Concept_Detail.addTableItem(Concept_Detail_id_out);
        Concept_Detail.addTableItem(Concept_Detail_concept_name_out);
        Concept_Detail.addTableItem(Concept_Detail_ts_code_out);
        Concept_Detail.addTableItem(Concept_Detail_name_out);
        Concept_Detail.addTableItem(Concept_Detail_in_date_out);
        Concept_Detail.addTableItem(Concept_Detail_out_date_out);

        SQLite_Tables.add(Concept_Detail);
    }


    static {
        //   // 【table_45_share_float 】    share_float  限售股解禁
        //
        // 您每分钟最多访问该接口80次，权限的具体详情访问：
        DB_Table Share_Float = new DB_Table("share_float");
        Share_Float.tableIndex = 45;
        Share_Float.viceTableIndex = 1;
        Share_Float.scoreLimit = 120;
        Share_Float.tableDesc = "限售股解禁数据";


        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();
//        String dateNameNow = df.format(calendar.getTime());
        String dateNameNow = "20191018";


        Table_Input_Param Share_Float_ts_code = new Table_Input_Param("ts_code", "str", "");
        Share_Float_ts_code.desc = "TS代码";

        Table_Input_Param Share_Float_ann_date = new Table_Input_Param("ann_date", "str", "");
        Share_Float_ann_date.desc = "公告日期";

        Table_Input_Param Share_Float_float_date = new Table_Input_Param("float_date", "str", dateNameNow);
        Share_Float_float_date.desc = "解禁日期";


        Table_Input_Param Share_Float_start_date = new Table_Input_Param("start_date", "str", "");
        Share_Float_start_date.desc = "解禁开始日期";

        Table_Input_Param Share_Float_end_date = new Table_Input_Param("end_date", "str", "");
        Share_Float_end_date.desc = "解禁结束日期";

        Share_Float.addTableInputParam(Share_Float_ts_code);
        Share_Float.addTableInputParam(Share_Float_ann_date);
        Share_Float.addTableInputParam(Share_Float_float_date);
        Share_Float.addTableInputParam(Share_Float_start_date);
        Share_Float.addTableInputParam(Share_Float_end_date);


        Table_Item Share_Float_out_ts_code = new Table_Item("ts_code", SQLITE_TYPE.TEXT, "TS股票代码");
        Table_Item Share_Float_out_ann_date = new Table_Item("ann_date", SQLITE_TYPE.TEXT, "公告日期");
        Table_Item Share_Float_out_float_date = new Table_Item("float_date", SQLITE_TYPE.TEXT, "解禁日期");
        Table_Item Share_Float_out_float_share = new Table_Item("float_share", SQLITE_TYPE.REAL, "流通股份");
        Table_Item Share_Float_out_float_ratio = new Table_Item("float_ratio", SQLITE_TYPE.REAL, "流通股份占总股本比率");
        Table_Item Share_Float_out_holder_name = new Table_Item("holder_name", SQLITE_TYPE.TEXT, "股东名称");
        Table_Item Share_Float_out_share_type = new Table_Item("share_type", SQLITE_TYPE.TEXT, "股份类型");


        Share_Float.addTableItem(Share_Float_out_ts_code);
        Share_Float.addTableItem(Share_Float_out_ann_date);
        Share_Float.addTableItem(Share_Float_out_float_date);
        Share_Float.addTableItem(Share_Float_out_float_share);
        Share_Float.addTableItem(Share_Float_out_float_ratio);
        Share_Float.addTableItem(Share_Float_out_holder_name);
        Share_Float.addTableItem(Share_Float_out_share_type);

        SQLite_Tables.add(Share_Float);
    }


    static {
//  【 table_46_block_trade 】 大宗交易
        DB_Table Block_Trade = new DB_Table("block_trade");
        Block_Trade.tableIndex = 46;
        Block_Trade.viceTableIndex = 1;
        Block_Trade.scoreLimit = 300;
        Block_Trade.tableDesc = "大宗交易";
        Table_Input_Param Block_Trade_trade_date = new Table_Input_Param("trade_date", "str", "");
        Block_Trade_trade_date.desc = "交易日期格式下同";
        Table_Input_Param Block_Trade_start_date = new Table_Input_Param("start_date", "str", "");
        Block_Trade_start_date.desc = "开始日期";
        Table_Input_Param Block_Trade_end_date = new Table_Input_Param("end_date", "str", "");
        Block_Trade_end_date.desc = "结束日期";
        Block_Trade.addTableInputParam(Block_Trade_trade_date);
        Block_Trade.addTableInputParam(Block_Trade_start_date);
        Block_Trade.addTableInputParam(Block_Trade_end_date);

        Table_Item Block_Trade_ts_code_out = new Table_Item("ts_code", SQLITE_TYPE.TEXT, "代码");
        Table_Item Block_Trade_trade_date_out = new Table_Item("trade_date", SQLITE_TYPE.TEXT, "交易日历");
        Table_Item Block_Trade_price_out = new Table_Item("price", SQLITE_TYPE.REAL, "成交价");
        Table_Item Block_Trade_vol_out = new Table_Item("vol", SQLITE_TYPE.REAL, "成交量万股");
        Table_Item Block_Trade_amount_out = new Table_Item("amount", SQLITE_TYPE.REAL, "成交金额");
        Table_Item Block_Trade_buyer_out = new Table_Item("buyer", SQLITE_TYPE.TEXT, "买方营业部");
        Table_Item Block_Trade_seller_out = new Table_Item("seller", SQLITE_TYPE.TEXT, "卖方营业部");
        Block_Trade.addTableItem(Block_Trade_ts_code_out);
        Block_Trade.addTableItem(Block_Trade_trade_date_out);
        Block_Trade.addTableItem(Block_Trade_price_out);
        Block_Trade.addTableItem(Block_Trade_vol_out);
        Block_Trade.addTableItem(Block_Trade_amount_out);
        Block_Trade.addTableItem(Block_Trade_buyer_out);
        Block_Trade.addTableItem(Block_Trade_seller_out);

        SQLite_Tables.add(Block_Trade);
    }


    static {
//  【 table_47_stk_account 】 获取股票账户开户数据，统计周期为一周
        DB_Table Stk_Account = new DB_Table("stk_account");
        Stk_Account.tableIndex = 47;
        Stk_Account.viceTableIndex = 1;
        Stk_Account.scoreLimit = 600;
        Stk_Account.tableDesc = "获取股票账户开户数据，统计周期为一周";
        Table_Input_Param Stk_Account_date = new Table_Input_Param("date", "str", "");
        Stk_Account_date.desc = "日期";
        Table_Input_Param Stk_Account_start_date = new Table_Input_Param("start_date", "str", "");
        Stk_Account_start_date.desc = "开始日期";
        Table_Input_Param Stk_Account_end_date = new Table_Input_Param("end_date", "str", "");
        Stk_Account_end_date.desc = "结束日期";
        Stk_Account.addTableInputParam(Stk_Account_date);
        Stk_Account.addTableInputParam(Stk_Account_start_date);
        Stk_Account.addTableInputParam(Stk_Account_end_date);

        Table_Item Stk_Account_date_out = new Table_Item("date", SQLITE_TYPE.TEXT, "统计周期");
        Table_Item Stk_Account_weekly_new_out = new Table_Item("weekly_new", SQLITE_TYPE.REAL, "本周新增万");
        Table_Item Stk_Account_total_out = new Table_Item("total", SQLITE_TYPE.REAL, "期末总账户数万");
        Table_Item Stk_Account_weekly_hold_out = new Table_Item("weekly_hold", SQLITE_TYPE.REAL, "本周持仓账户数万");
        Table_Item Stk_Account_weekly_trade_out = new Table_Item("weekly_trade", SQLITE_TYPE.REAL, "本周参与交易账户数万");
        Stk_Account.addTableItem(Stk_Account_date_out);
        Stk_Account.addTableItem(Stk_Account_weekly_new_out);
        Stk_Account.addTableItem(Stk_Account_total_out);
        Stk_Account.addTableItem(Stk_Account_weekly_hold_out);
        Stk_Account.addTableItem(Stk_Account_weekly_trade_out);

        SQLite_Tables.add(Stk_Account);
    }


    static {
//  【 table_48_stk_holdernumber 】 获取上市公司股东户数数据，数据不定期公布
        DB_Table Stk_Holdernumber = new DB_Table("stk_holdernumber");
        Stk_Holdernumber.tableIndex = 48;
        Stk_Holdernumber.viceTableIndex = 1;
        Stk_Holdernumber.scoreLimit = 600;
        Stk_Holdernumber.tableDesc = "获取上市公司股东户数数据，数据不定期公布";
        Table_Input_Param Stk_Holdernumber_enddate = new Table_Input_Param("enddate", "str", "");
        Stk_Holdernumber_enddate.desc = "截止日期";
        Table_Input_Param Stk_Holdernumber_start_date = new Table_Input_Param("start_date", "str", "");
        Stk_Holdernumber_start_date.desc = "公告开始日期";
        Table_Input_Param Stk_Holdernumber_end_date = new Table_Input_Param("end_date", "str", "");
        Stk_Holdernumber_end_date.desc = "公告结束日期";
        Stk_Holdernumber.addTableInputParam(Stk_Holdernumber_enddate);
        Stk_Holdernumber.addTableInputParam(Stk_Holdernumber_start_date);
        Stk_Holdernumber.addTableInputParam(Stk_Holdernumber_end_date);

        Table_Item Stk_Holdernumber_ts_code_out = new Table_Item("ts_code", SQLITE_TYPE.TEXT, "股票代码");
        Table_Item Stk_Holdernumber_ann_date_out = new Table_Item("ann_date", SQLITE_TYPE.TEXT, "公告日期");
        Table_Item Stk_Holdernumber_end_date_out = new Table_Item("end_date", SQLITE_TYPE.TEXT, "截止日期");
        Table_Item Stk_Holdernumber_holder_num_out = new Table_Item("holder_num", SQLITE_TYPE.INT, "股东户数");
        Stk_Holdernumber.addTableItem(Stk_Holdernumber_ts_code_out);
        Stk_Holdernumber.addTableItem(Stk_Holdernumber_ann_date_out);
        Stk_Holdernumber.addTableItem(Stk_Holdernumber_end_date_out);
        Stk_Holdernumber.addTableItem(Stk_Holdernumber_holder_num_out);

        SQLite_Tables.add(Stk_Holdernumber);
    }


    static {
//  【 table_49_stk_holdertrade 】 获取上市公司增减持数据，了解重要股东近期及历史上的股份增减变化
        DB_Table Stk_Holdertrade = new DB_Table("stk_holdertrade");
        Stk_Holdertrade.tableIndex = 49;
        Stk_Holdertrade.viceTableIndex = 1;
        Stk_Holdertrade.scoreLimit = 2000;
        Stk_Holdertrade.tableDesc = "获取上市公司增减持数据，了解重要股东近期及历史上的股份增减变化";
        Table_Input_Param Stk_Holdertrade_ann_date = new Table_Input_Param("ann_date", "str", "");
        Stk_Holdertrade_ann_date.desc = "公告日期";
        Table_Input_Param Stk_Holdertrade_start_date = new Table_Input_Param("start_date", "str", "");
        Stk_Holdertrade_start_date.desc = "公告开始日期";
        Table_Input_Param Stk_Holdertrade_end_date = new Table_Input_Param("end_date", "str", "");
        Stk_Holdertrade_end_date.desc = "公告结束日期";
        Table_Input_Param Stk_Holdertrade_trade_type = new Table_Input_Param("trade_type", "str", "");
        Stk_Holdertrade_trade_type.desc = "交易类型增持减持";
        Table_Input_Param Stk_Holdertrade_holder_type = new Table_Input_Param("holder_type", "str", "");
        Stk_Holdertrade_holder_type.desc = "股东类型公司个人高管";
        Stk_Holdertrade.addTableInputParam(Stk_Holdertrade_ann_date);
        Stk_Holdertrade.addTableInputParam(Stk_Holdertrade_start_date);
        Stk_Holdertrade.addTableInputParam(Stk_Holdertrade_end_date);
        Stk_Holdertrade.addTableInputParam(Stk_Holdertrade_trade_type);
        Stk_Holdertrade.addTableInputParam(Stk_Holdertrade_holder_type);

        Table_Item Stk_Holdertrade_ts_code_out = new Table_Item("ts_code", SQLITE_TYPE.TEXT, "代码");
        Table_Item Stk_Holdertrade_ann_date_out = new Table_Item("ann_date", SQLITE_TYPE.TEXT, "公告日期");
        Table_Item Stk_Holdertrade_holder_name_out = new Table_Item("holder_name", SQLITE_TYPE.TEXT, "股东名称");
        Table_Item Stk_Holdertrade_holder_type_out = new Table_Item("holder_type", SQLITE_TYPE.TEXT, "股东类型高管个人公司");
        Table_Item Stk_Holdertrade_in_de_out = new Table_Item("in_de", SQLITE_TYPE.TEXT, "类型增持减持");
        Table_Item Stk_Holdertrade_change_vol_out = new Table_Item("change_vol", SQLITE_TYPE.REAL, "变动数量");
        Table_Item Stk_Holdertrade_change_ratio_out = new Table_Item("change_ratio", SQLITE_TYPE.REAL, "占流通比例");
        Table_Item Stk_Holdertrade_after_share_out = new Table_Item("after_share", SQLITE_TYPE.REAL, "变动后持股");
        Table_Item Stk_Holdertrade_after_ratio_out = new Table_Item("after_ratio", SQLITE_TYPE.REAL, "变动后占流通比例");
        Table_Item Stk_Holdertrade_avg_price_out = new Table_Item("avg_price", SQLITE_TYPE.REAL, "平均价格");
        Table_Item Stk_Holdertrade_total_share_out = new Table_Item("total_share", SQLITE_TYPE.REAL, "持股总数");
        Table_Item Stk_Holdertrade_begin_date_out = new Table_Item("begin_date", SQLITE_TYPE.TEXT, "增减持开始日期");
        Table_Item Stk_Holdertrade_close_date_out = new Table_Item("close_date", SQLITE_TYPE.TEXT, "增减持结束日期");
        Stk_Holdertrade.addTableItem(Stk_Holdertrade_ts_code_out);
        Stk_Holdertrade.addTableItem(Stk_Holdertrade_ann_date_out);
        Stk_Holdertrade.addTableItem(Stk_Holdertrade_holder_name_out);
        Stk_Holdertrade.addTableItem(Stk_Holdertrade_holder_type_out);
        Stk_Holdertrade.addTableItem(Stk_Holdertrade_in_de_out);
        Stk_Holdertrade.addTableItem(Stk_Holdertrade_change_vol_out);
        Stk_Holdertrade.addTableItem(Stk_Holdertrade_change_ratio_out);
        Stk_Holdertrade.addTableItem(Stk_Holdertrade_after_share_out);
        Stk_Holdertrade.addTableItem(Stk_Holdertrade_after_ratio_out);
        Stk_Holdertrade.addTableItem(Stk_Holdertrade_avg_price_out);
        Stk_Holdertrade.addTableItem(Stk_Holdertrade_total_share_out);
        Stk_Holdertrade.addTableItem(Stk_Holdertrade_begin_date_out);
        Stk_Holdertrade.addTableItem(Stk_Holdertrade_close_date_out);

        SQLite_Tables.add(Stk_Holdertrade);
    }


    static {
//  【 table_50_index_basic 】 获取指数基础信息。
        DB_Table Index_Basic = new DB_Table("index_basic");
        Index_Basic.tableIndex = 50;
        Index_Basic.viceTableIndex = 1;
        Index_Basic.tableDesc = "获取指数基础信息。";
        Table_Input_Param Index_Basic_market_SW = new Table_Input_Param("market", "str", "SW");
        Index_Basic_market_SW.desc = "交易所或服务商";
        Table_Input_Param Index_Basic_publisher = new Table_Input_Param("publisher", "str", "");
        Index_Basic_publisher.desc = "发布商";
        Table_Input_Param Index_Basic_category = new Table_Input_Param("category", "str", "");
        Index_Basic_category.desc = "指数类别";
        Index_Basic.addTableInputParam(Index_Basic_market_SW);
        Index_Basic.addTableInputParam(Index_Basic_publisher);
        Index_Basic.addTableInputParam(Index_Basic_category);


        Table_Item Index_Basic_ts_code_out = new Table_Item("ts_code", SQLITE_TYPE.TEXT, "代码");
        Table_Item Index_Basic_name_out = new Table_Item("name", SQLITE_TYPE.TEXT, "简称");
        Table_Item Index_Basic_fullname_out = new Table_Item("fullname", SQLITE_TYPE.TEXT, "指数全称");
        Table_Item Index_Basic_market_out = new Table_Item("market", SQLITE_TYPE.TEXT, "市场");
        Table_Item Index_Basic_publisher_out = new Table_Item("publisher", SQLITE_TYPE.TEXT, "发布方");
        Table_Item Index_Basic_index_type_out = new Table_Item("index_type", SQLITE_TYPE.TEXT, "指数风格");
        Table_Item Index_Basic_category_out = new Table_Item("category", SQLITE_TYPE.TEXT, "指数类别");
        Table_Item Index_Basic_base_date_out = new Table_Item("base_date", SQLITE_TYPE.TEXT, "基期");
        Table_Item Index_Basic_base_point_out = new Table_Item("base_point", SQLITE_TYPE.REAL, "基点");
        Table_Item Index_Basic_list_date_out = new Table_Item("list_date", SQLITE_TYPE.TEXT, "发布日期");
        Table_Item Index_Basic_weight_rule_out = new Table_Item("weight_rule", SQLITE_TYPE.TEXT, "加权方式");
        Table_Item Index_Basic_desc_out = new Table_Item("desc", SQLITE_TYPE.TEXT, "描述");
        Table_Item Index_Basic_exp_date_out = new Table_Item("exp_date", SQLITE_TYPE.TEXT, "终止日期");
        Index_Basic.addTableItem(Index_Basic_ts_code_out);
        Index_Basic.addTableItem(Index_Basic_name_out);
        Index_Basic.addTableItem(Index_Basic_fullname_out);
        Index_Basic.addTableItem(Index_Basic_market_out);
        Index_Basic.addTableItem(Index_Basic_publisher_out);
        Index_Basic.addTableItem(Index_Basic_index_type_out);
        Index_Basic.addTableItem(Index_Basic_category_out);
        Index_Basic.addTableItem(Index_Basic_base_date_out);
        Index_Basic.addTableItem(Index_Basic_base_point_out);
        Index_Basic.addTableItem(Index_Basic_list_date_out);
        Index_Basic.addTableItem(Index_Basic_weight_rule_out);
        Index_Basic.addTableItem(Index_Basic_desc_out);
        Index_Basic.addTableItem(Index_Basic_exp_date_out);

        //
        Table_Input_Param Index_Basic_market_MSCI = new Table_Input_Param("market", "str", "MSCI");
        Index_Basic_market_MSCI.desc = "交易所或服务商-MSCI-MSCI指数";

        DB_Table Index_Basic_Table_MSCI = new DB_Table(Index_Basic);
        Index_Basic_Table_MSCI.replaceInputParam(Index_Basic_market_MSCI);

        //
        Table_Input_Param Index_Basic_market_CSI = new Table_Input_Param("market", "str", "CSI");
        Index_Basic_market_CSI.desc = "交易所或服务商-CSI-中证指数";


        //
        Table_Input_Param Index_Basic_market_SSE = new Table_Input_Param("market", "str", "SSE");
        Index_Basic_market_SSE.desc = "交易所或服务商-SSE-上交所指数";

        DB_Table Index_Basic_Table_SSE = new DB_Table(Index_Basic);
        Index_Basic_Table_SSE.replaceInputParam(Index_Basic_market_SSE);

        //
        Table_Input_Param Index_Basic_market_SZSE = new Table_Input_Param("market", "str", "SZSE");
        Index_Basic_market_SZSE.desc = "交易所或服务商-SZSE-深交所指数";

        DB_Table Index_Basic_Table_SZSE = new DB_Table(Index_Basic);
        Index_Basic_Table_SZSE.replaceInputParam(Index_Basic_market_SZSE);

        //
        Table_Input_Param Index_Basic_market_CICC = new Table_Input_Param("market", "str", "CICC");
        Index_Basic_market_CICC.desc = "交易所或服务商-CICC-中金所指数";

        DB_Table Index_Basic_Table_CICC = new DB_Table(Index_Basic);
        Index_Basic_Table_CICC.replaceInputParam(Index_Basic_market_CICC);

        //
        Table_Input_Param Index_Basic_market_OTH = new Table_Input_Param("market", "str", "OTH");
        Index_Basic_market_OTH.desc = "交易所或服务商-OTH-其他指数";

        DB_Table Index_Basic_Table_OTH = new DB_Table(Index_Basic);
        Index_Basic_Table_OTH.replaceInputParam(Index_Basic_market_OTH);


        SQLite_Tables.add(Index_Basic);
        SQLite_Tables.add(Index_Basic_Table_MSCI);
        SQLite_Tables.add(Index_Basic_Table_SSE);
        SQLite_Tables.add(Index_Basic_Table_SZSE);
        SQLite_Tables.add(Index_Basic_Table_CICC);
        SQLite_Tables.add(Index_Basic_Table_OTH);

//=====================
        DB_Table Index_Basic_Table_CSI = new DB_Table(Index_Basic);
        Index_Basic_Table_CSI.replaceInputParam(Index_Basic_market_CSI);
        String[] CSI_Caegoty = {
                "主题指数",
                "规模指数",
                "策略指数",
                "风格指数",
                "综合指数",
                "成长指数",
                "价值指数",
                "有色指数",
                "化工指数",
                "能源指数",
                "其他指数",
                "外汇指数",
                "基金指数",
                "商品指数",
                "债券指数",
                "行业指数",
                "贵金属指数",
                "农副产品指数",
                "软商品指数",
                "油脂油料指数",
                "非金属建材指数",
                "煤焦钢矿指数",
                "谷物指数"};

//        SQLite_Tables.add();  // 太长!!!  需要分解
        for (int i = 0; i < CSI_Caegoty.length; i++) {
            DB_Table  Index_Basic_Table_CSI_sub = new DB_Table(Index_Basic_Table_CSI);
            Table_Input_Param Input_CSI_sub = new Table_Input_Param("category", "str", "");
            Index_Basic_Table_CSI_sub.replaceInputParam(Input_CSI_sub);
            Input_CSI_sub.paramValue=CSI_Caegoty[i];
            SQLite_Tables.add(Index_Basic_Table_CSI_sub);
        }

    }


    static {
//  【 table_51_index_daily 】 获取指数每日行情，还可以通过bar接口获取。由于服务器压力，目前规则是单次调取最多取8000行记录，可以设置start和end日期补全。指数行情也可以通过通用行情接口获取数据．
        DB_Table Index_Daily = new DB_Table("index_daily");
        Index_Daily.tableIndex = 51;
        Index_Daily.viceTableIndex = 1;
        Index_Daily.scoreLimit = 200;
        Index_Daily.tableDesc = "获取指数每日行情，还可以通过bar接口获取。由于服务器压力，目前规则是单次调取最多取8000行记录，可以设置start和end日期补全。指数行情也可以通过通用行情接口获取数据．";
        Table_Input_Param Index_Daily_ts_code = new Table_Input_Param("ts_code", "str", "");
        Index_Daily_ts_code.desc = "指数代码";
        Table_Input_Param Index_Daily_trade_date = new Table_Input_Param("trade_date", "str", "");
        Index_Daily_trade_date.desc = "交易日期日期格式下同";
        Table_Input_Param Index_Daily_start_date = new Table_Input_Param("start_date", "str", "");
        Index_Daily_start_date.desc = "开始日期";
        Table_Input_Param Index_Daily_end_date = new Table_Input_Param("end_date", "None", "");
        Index_Daily_end_date.desc = "结束日期";
        Index_Daily.addTableInputParam(Index_Daily_ts_code);
        Index_Daily.addTableInputParam(Index_Daily_trade_date);
        Index_Daily.addTableInputParam(Index_Daily_start_date);
        Index_Daily.addTableInputParam(Index_Daily_end_date);

        Table_Item Index_Daily_ts_code_out = new Table_Item("ts_code", SQLITE_TYPE.TEXT, "指数代码");
        Table_Item Index_Daily_trade_date_out = new Table_Item("trade_date", SQLITE_TYPE.TEXT, "交易日");
        Table_Item Index_Daily_close_out = new Table_Item("close", SQLITE_TYPE.REAL, "收盘点位");
        Table_Item Index_Daily_open_out = new Table_Item("open", SQLITE_TYPE.REAL, "开盘点位");
        Table_Item Index_Daily_high_out = new Table_Item("high", SQLITE_TYPE.REAL, "最高点位");
        Table_Item Index_Daily_low_out = new Table_Item("low", SQLITE_TYPE.REAL, "最低点位");
        Table_Item Index_Daily_pre_close_out = new Table_Item("pre_close", SQLITE_TYPE.REAL, "昨日收盘点");
        Table_Item Index_Daily_change_out = new Table_Item("change", SQLITE_TYPE.REAL, "涨跌点");
        Table_Item Index_Daily_pct_chg_out = new Table_Item("pct_chg", SQLITE_TYPE.REAL, "涨跌幅");
        Table_Item Index_Daily_vol_out = new Table_Item("vol", SQLITE_TYPE.REAL, "成交量手");
        Table_Item Index_Daily_amount_out = new Table_Item("amount", SQLITE_TYPE.REAL, "成交额千元");
        Index_Daily.addTableItem(Index_Daily_ts_code_out);
        Index_Daily.addTableItem(Index_Daily_trade_date_out);
        Index_Daily.addTableItem(Index_Daily_close_out);
        Index_Daily.addTableItem(Index_Daily_open_out);
        Index_Daily.addTableItem(Index_Daily_high_out);
        Index_Daily.addTableItem(Index_Daily_low_out);
        Index_Daily.addTableItem(Index_Daily_pre_close_out);
        Index_Daily.addTableItem(Index_Daily_change_out);
        Index_Daily.addTableItem(Index_Daily_pct_chg_out);
        Index_Daily.addTableItem(Index_Daily_vol_out);
        Index_Daily.addTableItem(Index_Daily_amount_out);

        SQLite_Tables.add(Index_Daily);
    }


    static {
//  【 table_52_index_weekly 】 获取指数周线行情
        DB_Table Index_Weekly = new DB_Table("index_weekly");
        Index_Weekly.tableIndex = 52;
        Index_Weekly.viceTableIndex = 1;
        Index_Weekly.scoreLimit = 600;
        Index_Weekly.tableDesc = "获取指数周线行情";
        Table_Input_Param Index_Weekly_trade_date = new Table_Input_Param("trade_date", "str", "");
        Index_Weekly_trade_date.desc = "交易日期";
        Table_Input_Param Index_Weekly_start_date = new Table_Input_Param("start_date", "str", "");
        Index_Weekly_start_date.desc = "开始日期";
        Table_Input_Param Index_Weekly_end_date = new Table_Input_Param("end_date", "str", "");
        Index_Weekly_end_date.desc = "结束日期";
        Index_Weekly.addTableInputParam(Index_Weekly_trade_date);
        Index_Weekly.addTableInputParam(Index_Weekly_start_date);
        Index_Weekly.addTableInputParam(Index_Weekly_end_date);

        Table_Item Index_Weekly_ts_code_out = new Table_Item("ts_code", SQLITE_TYPE.TEXT, "指数代码");
        Table_Item Index_Weekly_trade_date_out = new Table_Item("trade_date", SQLITE_TYPE.TEXT, "交易日");
        Table_Item Index_Weekly_close_out = new Table_Item("close", SQLITE_TYPE.REAL, "收盘点位");
        Table_Item Index_Weekly_open_out = new Table_Item("open", SQLITE_TYPE.REAL, "开盘点位");
        Table_Item Index_Weekly_high_out = new Table_Item("high", SQLITE_TYPE.REAL, "最高点位");
        Table_Item Index_Weekly_low_out = new Table_Item("low", SQLITE_TYPE.REAL, "最低点位");
        Table_Item Index_Weekly_pre_close_out = new Table_Item("pre_close", SQLITE_TYPE.REAL, "昨日收盘点");
        Table_Item Index_Weekly_change_out = new Table_Item("change", SQLITE_TYPE.REAL, "涨跌点位");
        Table_Item Index_Weekly_pct_chg_out = new Table_Item("pct_chg", SQLITE_TYPE.REAL, "涨跌幅");
        Table_Item Index_Weekly_vol_out = new Table_Item("vol", SQLITE_TYPE.REAL, "成交量");
        Table_Item Index_Weekly_amount_out = new Table_Item("amount", SQLITE_TYPE.REAL, "成交额");
        Index_Weekly.addTableItem(Index_Weekly_ts_code_out);
        Index_Weekly.addTableItem(Index_Weekly_trade_date_out);
        Index_Weekly.addTableItem(Index_Weekly_close_out);
        Index_Weekly.addTableItem(Index_Weekly_open_out);
        Index_Weekly.addTableItem(Index_Weekly_high_out);
        Index_Weekly.addTableItem(Index_Weekly_low_out);
        Index_Weekly.addTableItem(Index_Weekly_pre_close_out);
        Index_Weekly.addTableItem(Index_Weekly_change_out);
        Index_Weekly.addTableItem(Index_Weekly_pct_chg_out);
        Index_Weekly.addTableItem(Index_Weekly_vol_out);
        Index_Weekly.addTableItem(Index_Weekly_amount_out);

        SQLite_Tables.add(Index_Weekly);
    }


    static {
//  【 table_53_index_monthly 】 获取指数月线行情,每月更新一次
        DB_Table Index_Monthly = new DB_Table("index_monthly");
        Index_Monthly.tableIndex = 53;
        Index_Monthly.viceTableIndex = 1;
        Index_Monthly.scoreLimit = 600;
        Index_Monthly.tableDesc = "获取指数月线行情,每月更新一次";
        Table_Input_Param Index_Monthly_trade_date = new Table_Input_Param("trade_date", "str", "");
        Index_Monthly_trade_date.desc = "交易日期";
        Table_Input_Param Index_Monthly_start_date = new Table_Input_Param("start_date", "str", "");
        Index_Monthly_start_date.desc = "开始日期";
        Table_Input_Param Index_Monthly_end_date = new Table_Input_Param("end_date", "str", "");
        Index_Monthly_end_date.desc = "结束日期";
        Index_Monthly.addTableInputParam(Index_Monthly_trade_date);
        Index_Monthly.addTableInputParam(Index_Monthly_start_date);
        Index_Monthly.addTableInputParam(Index_Monthly_end_date);

        Table_Item Index_Monthly_ts_code_out = new Table_Item("ts_code", SQLITE_TYPE.TEXT, "指数代码");
        Table_Item Index_Monthly_trade_date_out = new Table_Item("trade_date", SQLITE_TYPE.TEXT, "交易日");
        Table_Item Index_Monthly_close_out = new Table_Item("close", SQLITE_TYPE.REAL, "收盘点位");
        Table_Item Index_Monthly_open_out = new Table_Item("open", SQLITE_TYPE.REAL, "开盘点位");
        Table_Item Index_Monthly_high_out = new Table_Item("high", SQLITE_TYPE.REAL, "最高点位");
        Table_Item Index_Monthly_low_out = new Table_Item("low", SQLITE_TYPE.REAL, "最低点位");
        Table_Item Index_Monthly_pre_close_out = new Table_Item("pre_close", SQLITE_TYPE.REAL, "昨日收盘点");
        Table_Item Index_Monthly_change_out = new Table_Item("change", SQLITE_TYPE.REAL, "涨跌点位");
        Table_Item Index_Monthly_pct_chg_out = new Table_Item("pct_chg", SQLITE_TYPE.REAL, "涨跌幅");
        Table_Item Index_Monthly_vol_out = new Table_Item("vol", SQLITE_TYPE.REAL, "成交量");
        Table_Item Index_Monthly_amount_out = new Table_Item("amount", SQLITE_TYPE.REAL, "成交额");
        Index_Monthly.addTableItem(Index_Monthly_ts_code_out);
        Index_Monthly.addTableItem(Index_Monthly_trade_date_out);
        Index_Monthly.addTableItem(Index_Monthly_close_out);
        Index_Monthly.addTableItem(Index_Monthly_open_out);
        Index_Monthly.addTableItem(Index_Monthly_high_out);
        Index_Monthly.addTableItem(Index_Monthly_low_out);
        Index_Monthly.addTableItem(Index_Monthly_pre_close_out);
        Index_Monthly.addTableItem(Index_Monthly_change_out);
        Index_Monthly.addTableItem(Index_Monthly_pct_chg_out);
        Index_Monthly.addTableItem(Index_Monthly_vol_out);
        Index_Monthly.addTableItem(Index_Monthly_amount_out);

        SQLite_Tables.add(Index_Monthly);
    }


    static {
//  【 table_54_index_weight 】 获取各类指数成分和权重，月度数据 ，如需日度指数成分和权重，请联系 waditu@163.com
        DB_Table Index_Weight = new DB_Table("index_weight");
        Index_Weight.tableIndex = 54;
        Index_Weight.viceTableIndex = 1;
        Index_Weight.scoreLimit = 400;
        Index_Weight.tableDesc = "获取各类指数成分和权重，月度数据 ，如需日度指数成分和权重，请联系 waditu@163.com";
        Table_Input_Param Index_Weight_index_code = new Table_Input_Param("index_code", "str", "");
        Index_Weight_index_code.desc = "指数代码二选一";
        Table_Input_Param Index_Weight_trade_date = new Table_Input_Param("trade_date", "str", "");
        Index_Weight_trade_date.desc = "交易日期二选一";
        Table_Input_Param Index_Weight_start_date = new Table_Input_Param("start_date", "str", "");
        Index_Weight_start_date.desc = "开始日期";
        Table_Input_Param Index_Weight_end_date = new Table_Input_Param("end_date", "None", "");
        Index_Weight_end_date.desc = "结束日期";
        Index_Weight.addTableInputParam(Index_Weight_index_code);
        Index_Weight.addTableInputParam(Index_Weight_trade_date);
        Index_Weight.addTableInputParam(Index_Weight_start_date);
        Index_Weight.addTableInputParam(Index_Weight_end_date);

        Table_Item Index_Weight_index_code_out = new Table_Item("index_code", SQLITE_TYPE.TEXT, "指数代码");
        Table_Item Index_Weight_con_code_out = new Table_Item("con_code", SQLITE_TYPE.TEXT, "成分代码");
        Table_Item Index_Weight_trade_date_out = new Table_Item("trade_date", SQLITE_TYPE.TEXT, "交易日期");
        Table_Item Index_Weight_weight_out = new Table_Item("weight", SQLITE_TYPE.REAL, "权重");
        Index_Weight.addTableItem(Index_Weight_index_code_out);
        Index_Weight.addTableItem(Index_Weight_con_code_out);
        Index_Weight.addTableItem(Index_Weight_trade_date_out);
        Index_Weight.addTableItem(Index_Weight_weight_out);

        SQLite_Tables.add(Index_Weight);
    }


    static {
//  【 table_55_index_dailybasic 】 目前只提供上证综指，深证成指，上证50，中证500，中小板指，创业板指的每日指标数据
        DB_Table Index_Dailybasic = new DB_Table("index_dailybasic");
        Index_Dailybasic.tableIndex = 55;
        Index_Dailybasic.viceTableIndex = 1;
        Index_Dailybasic.scoreLimit = 400;
        Index_Dailybasic.tableDesc = "目前只提供上证综指，深证成指，上证50，中证500，中小板指，创业板指的每日指标数据";
        Table_Input_Param Index_Dailybasic_trade_date = new Table_Input_Param("trade_date", "str", "");
        Index_Dailybasic_trade_date.desc = "交易日期格式比如下同";
        Table_Input_Param Index_Dailybasic_start_date = new Table_Input_Param("start_date", "str", "");
        Index_Dailybasic_start_date.desc = "开始日期";
        Table_Input_Param Index_Dailybasic_end_date = new Table_Input_Param("end_date", "str", "");
        Index_Dailybasic_end_date.desc = "结束日期";
        Index_Dailybasic.addTableInputParam(Index_Dailybasic_trade_date);
        Index_Dailybasic.addTableInputParam(Index_Dailybasic_start_date);
        Index_Dailybasic.addTableInputParam(Index_Dailybasic_end_date);

        Table_Item Index_Dailybasic_ts_code_out = new Table_Item("ts_code", SQLITE_TYPE.TEXT, "代码");
        Table_Item Index_Dailybasic_trade_date_out = new Table_Item("trade_date", SQLITE_TYPE.TEXT, "交易日期");
        Table_Item Index_Dailybasic_total_mv_out = new Table_Item("total_mv", SQLITE_TYPE.REAL, "当日总市值元");
        Table_Item Index_Dailybasic_float_mv_out = new Table_Item("float_mv", SQLITE_TYPE.REAL, "当日流通市值元");
        Table_Item Index_Dailybasic_total_share_out = new Table_Item("total_share", SQLITE_TYPE.REAL, "当日总股本股");
        Table_Item Index_Dailybasic_float_share_out = new Table_Item("float_share", SQLITE_TYPE.REAL, "当日流通股本股");
        Table_Item Index_Dailybasic_free_share_out = new Table_Item("free_share", SQLITE_TYPE.REAL, "当日自由流通股本股");
        Table_Item Index_Dailybasic_turnover_rate_out = new Table_Item("turnover_rate", SQLITE_TYPE.REAL, "换手率");
        Table_Item Index_Dailybasic_turnover_rate_f_out = new Table_Item("turnover_rate_f", SQLITE_TYPE.REAL, "换手率基于自由流通股本");
        Table_Item Index_Dailybasic_pe_out = new Table_Item("pe", SQLITE_TYPE.REAL, "市盈率");
        Table_Item Index_Dailybasic_pe_ttm_out = new Table_Item("pe_ttm", SQLITE_TYPE.REAL, "市盈率");
        Table_Item Index_Dailybasic_pb_out = new Table_Item("pb", SQLITE_TYPE.REAL, "市净率");
        Index_Dailybasic.addTableItem(Index_Dailybasic_ts_code_out);
        Index_Dailybasic.addTableItem(Index_Dailybasic_trade_date_out);
        Index_Dailybasic.addTableItem(Index_Dailybasic_total_mv_out);
        Index_Dailybasic.addTableItem(Index_Dailybasic_float_mv_out);
        Index_Dailybasic.addTableItem(Index_Dailybasic_total_share_out);
        Index_Dailybasic.addTableItem(Index_Dailybasic_float_share_out);
        Index_Dailybasic.addTableItem(Index_Dailybasic_free_share_out);
        Index_Dailybasic.addTableItem(Index_Dailybasic_turnover_rate_out);
        Index_Dailybasic.addTableItem(Index_Dailybasic_turnover_rate_f_out);
        Index_Dailybasic.addTableItem(Index_Dailybasic_pe_out);
        Index_Dailybasic.addTableItem(Index_Dailybasic_pe_ttm_out);
        Index_Dailybasic.addTableItem(Index_Dailybasic_pb_out);

        SQLite_Tables.add(Index_Dailybasic);
    }


    static {
//  【 table_56_index_classify 】 获取申万行业分类，包括申万28个一级分类，104个二级分类，227个三级分类的列表信息
        DB_Table Index_Classify = new DB_Table("index_classify");
        Index_Classify.tableIndex = 56;
        Index_Classify.viceTableIndex = 1;
        Index_Classify.scoreLimit = 2000;
        Index_Classify.tableDesc = "获取申万行业分类，包括申万28个一级分类，104个二级分类，227个三级分类的列表信息";
        Table_Input_Param Index_Classify_index_code = new Table_Input_Param("index_code", "str", "");
        Index_Classify_index_code.desc = "指数代码";
        Table_Input_Param Index_Classify_level = new Table_Input_Param("level", "str", "");
        Index_Classify_level.desc = "行业分级";
        Table_Input_Param Index_Classify_src = new Table_Input_Param("src", "str", "");
        Index_Classify_src.desc = "指数来源申万";
        Index_Classify.addTableInputParam(Index_Classify_index_code);
        Index_Classify.addTableInputParam(Index_Classify_level);
        Index_Classify.addTableInputParam(Index_Classify_src);

        Table_Item Index_Classify_index_code_out = new Table_Item("index_code", SQLITE_TYPE.TEXT, "指数代码");
        Table_Item Index_Classify_industry_name_out = new Table_Item("industry_name", SQLITE_TYPE.TEXT, "行业名称");
        Table_Item Index_Classify_level_out = new Table_Item("level", SQLITE_TYPE.TEXT, "行业名称");
        Table_Item Index_Classify_industry_code_out = new Table_Item("industry_code", SQLITE_TYPE.TEXT, "行业代码");
        Table_Item Index_Classify_src_out = new Table_Item("src", SQLITE_TYPE.TEXT, "行业分类申万");
        Index_Classify.addTableItem(Index_Classify_index_code_out);
        Index_Classify.addTableItem(Index_Classify_industry_name_out);
        Index_Classify.addTableItem(Index_Classify_level_out);
        Index_Classify.addTableItem(Index_Classify_industry_code_out);
        Index_Classify.addTableItem(Index_Classify_src_out);

        SQLite_Tables.add(Index_Classify);
    }


    static {
//  【 table_57_index_member 】 申万行业成分
        DB_Table Index_Member = new DB_Table("index_member");
        Index_Member.tableIndex = 57;
        Index_Member.viceTableIndex = 1;
        Index_Member.scoreLimit = 2000;
        Index_Member.tableDesc = "申万行业成分";
        Table_Input_Param Index_Member_index_code = new Table_Input_Param("index_code", "str", "");
        Index_Member_index_code.desc = "指数代码";
        Table_Input_Param Index_Member_ts_code = new Table_Input_Param("ts_code", "str", "");
        Index_Member_ts_code.desc = "股票代码";
        Table_Input_Param Index_Member_is_new = new Table_Input_Param("is_new", "str", "");
        Index_Member_is_new.desc = "是否最新默认为是";
        Index_Member.addTableInputParam(Index_Member_index_code);
        Index_Member.addTableInputParam(Index_Member_ts_code);
        Index_Member.addTableInputParam(Index_Member_is_new);

        Table_Item Index_Member_index_code_out = new Table_Item("index_code", SQLITE_TYPE.TEXT, "指数代码");
        Table_Item Index_Member_index_name_out = new Table_Item("index_name", SQLITE_TYPE.TEXT, "指数名称");
        Table_Item Index_Member_con_code_out = new Table_Item("con_code", SQLITE_TYPE.TEXT, "成分股票代码");
        Table_Item Index_Member_con_name_out = new Table_Item("con_name", SQLITE_TYPE.TEXT, "成分股票名称");
        Table_Item Index_Member_in_date_out = new Table_Item("in_date", SQLITE_TYPE.TEXT, "纳入日期");
        Table_Item Index_Member_out_date_out = new Table_Item("out_date", SQLITE_TYPE.TEXT, "剔除日期");
        Table_Item Index_Member_is_new_out = new Table_Item("is_new", SQLITE_TYPE.TEXT, "是否最新是否");
        Index_Member.addTableItem(Index_Member_index_code_out);
        Index_Member.addTableItem(Index_Member_index_name_out);
        Index_Member.addTableItem(Index_Member_con_code_out);
        Index_Member.addTableItem(Index_Member_con_name_out);
        Index_Member.addTableItem(Index_Member_in_date_out);
        Index_Member.addTableItem(Index_Member_out_date_out);
        Index_Member.addTableItem(Index_Member_is_new_out);

        SQLite_Tables.add(Index_Member);
    }


    static class DB_Table {
        String tableName;  //
        int tableIndex;  //主键 唯一的     用于区别哪个表
        // 副索引    用于区别 哪个是正的表    执行 创表的那个 表的索引 为 1   其他表的副索引不为1
        int viceTableIndex;
        String tableDesc; //   当前数据说明
        int scoreLimit;   // 调用积分限制
        boolean request_need_tscode;  // 调用参数是否需要 ts_code参数

        ArrayList<Table_Item> fieldList;    // 创建表的
        ArrayList<String> filedNameList;  // 项名称的集合
        ArrayList<Table_Data> tableData;    // 所有数据的集合
        ArrayList<Table_Input_Param> inputParamList;

        DB_Table(String name) {
            this.tableName = name;
            fieldList = new ArrayList<Table_Item>();
            filedNameList = new ArrayList<String>();
            tableData = new ArrayList<Table_Data>();
            this.inputParamList = new ArrayList<Table_Input_Param>();
        }


        public DB_Table(String tableName, int tableIndex, int viceTableIndex, String tableDesc, int scoreLimit, boolean request_need_tscode, ArrayList<Table_Item> fieldList, ArrayList<String> filedNameList, ArrayList<Table_Data> tableData, ArrayList<Table_Input_Param> inputParamList) {
            this.tableName = tableName;
            this.tableIndex = tableIndex;
            this.viceTableIndex = viceTableIndex + 1;
            this.tableDesc = tableDesc;
            this.scoreLimit = scoreLimit;
            this.request_need_tscode = request_need_tscode;
            this.fieldList = new ArrayList<Table_Item>();
            this.fieldList = fieldList;

            this.filedNameList = filedNameList;
            this.tableData = tableData;
            this.inputParamList = inputParamList;
        }

        DB_Table(DB_Table copyObj) {
            this.tableName = copyObj.tableName;
            this.tableIndex = copyObj.tableIndex;
            this.viceTableIndex = copyObj.viceTableIndex + 1;
            this.tableDesc = copyObj.tableDesc;
            this.scoreLimit = copyObj.scoreLimit;
            this.request_need_tscode = copyObj.request_need_tscode;
            this.fieldList = new ArrayList<Table_Item>();
            this.fieldList.addAll(copyObj.fieldList);

            this.filedNameList = new ArrayList<String>();
            this.filedNameList.addAll(copyObj.filedNameList);

            this.tableData = new ArrayList<Table_Data>();
            this.tableData.addAll(copyObj.tableData);

            this.inputParamList = new ArrayList<Table_Input_Param>();
            this.inputParamList.addAll(copyObj.inputParamList);
        }


        void replaceInputParam(Table_Input_Param input) {
            String name = input.paramName;
            Table_Input_Param needReplaceParam = null;
            for (int i = 0; i < inputParamList.size(); i++) {
                Table_Input_Param item = inputParamList.get(i);
                if (name.equals(item.paramName)) {
                    needReplaceParam = item;
                }
            }
            if (needReplaceParam != null) {
                inputParamList.remove(needReplaceParam);
                inputParamList.add(input);
            }


        }


        void insertTableData2DB() {
            try {
                Connection conn = DriverManager.getConnection(SQLITE_CONNECT_URL);

                conn.setAutoCommit(false);

                Statement state = conn.createStatement();


                for (int i = 0; i < this.tableData.size(); i++) {
                    String insertSql = this.tableData.get(i).buildInsertSql();
                    state.addBatch(insertSql);
                    // System.out.println("插入语句索引:"+i+"   SQL语句:"+insertSql);
                }

                state.executeBatch();
                conn.commit();
                conn.setAutoCommit(true);
                state.close();
                conn.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        void initJsonData(String mJsonData, String tableName) {
            if ("".equals(mJsonData)) {
                System.out.println("initJsonData 获取到的Json数据为空!");
                return;
            }

            int resultCode = getCodeFromResultJson(mJsonData);
            if (resultCode != 0) {
                System.out.println(" JsonData :" + mJsonData);
                System.out.println("Json返回的 resultCode非0 调用不成功!   ");
            }

            String dataResult = getDataFromResultJson(mJsonData);

            JSONObject dataJsonObj = JSONObject.parseObject(dataResult);
            //   System.out.println("dataResult = "+ dataResult);

            JSONArray fieldJsonArr = dataJsonObj.getJSONArray("fields");
            //  JSONArray fieldJsonArr = JSONObject.parseArray(field.toString());
            ArrayList<String> jsonFieldList = new ArrayList<String>();

            for (int i = 0; i < fieldJsonArr.size(); i++) {
                jsonFieldList.add(fieldJsonArr.get(i).toString());

            }


            //   System.out.println("fieldJsonArr.size = "+ fieldJsonArr.size());
            JSONArray itemsJsonArr = dataJsonObj.getJSONArray("items");
            //JSONArray itemsJsonArr = JSONObject.parseArray(items.toString());
            //  System.out.println("itemsJsonArr.size = "+ itemsJsonArr.size());


            for (int i = 0; i < itemsJsonArr.size(); i++) {
                //     Object dataItem =  itemsJsonArr.get(i);
                JSONArray dataItem = itemsJsonArr.getJSONArray(i);
                Table_Data rowData = new Table_Data(jsonFieldList, tableName);
                for (int j = 0; j < dataItem.size(); j++) {
                    String fieldKey = rowData.fieldNameList.get(j).trim();
                    String value = "";
                    if (dataItem.get(j) == null) {
                        value = "null";
                    } else {
                        value = dataItem.get(j).toString();
                        // 处理这样的字符串  "\"The MSCI China
                        value = value.replace("\"", "");
                    }
                    rowData.fieldAndValueMap.put(fieldKey, value);
                    // 添加对ts_code的收集

                    if ("ts_code".equals(fieldKey)) {
                        TS_CODE_List.add(value);
                    }


                    //  System.out.println("item"+i+": field"+j+ " = "+dataItem.get(j));

                }
                tableData.add(rowData);
                if (this.tableIndex == 1) {  // stock_basic
                    TSCODE_NAME_MAP.put(rowData.fieldAndValueMap.get("ts_code"), rowData.fieldAndValueMap.get("name"));
                }


            }
//            JSONObject jsonObj = JSONObject.parseObject(mJsonData);
//            Set<Map.Entry<String, Object>> keySet = jsonObj.entrySet();
//            System.out.println("keySet.size ="+ keySet.size());
//            for (Map.Entry<String, Object> entry : keySet) {
//
//                String jsonValue =   entry.getValue().toString();
//                String jsonKey =   entry.getKey();
//                System.out.println("Key:"+jsonKey);
//                System.out.println("Value:"+jsonValue);
//
//            }


        }

        int getCodeFromResultJson(String mJsonData) {
            int result = -1;
            // code 是 int 类型
            result = JSONObject.parseObject(mJsonData).getIntValue("code");
            //  System.out.println("codeStr = "+ result);
//          if("1".equals(codeStr.trim())){
//              result = 1;
//          }
            return result;
        }

        String getDataFromResultJson(String mJsonData) {

            Object obj = JSONObject.parseObject(mJsonData).get("data");
            if (obj == null) {
                return "";
            }
            return obj.toString();

        }

/*

{"api_name": "stock_basic", "token": "43acb9a5ddc2cf73c6c4ea54796748f965457ed57daaa736bb778ea2",
"params": {"list_stauts":"P"},
"fields": "ts_code,symbol,name,area,industry,fullname,enname,market,exchange,curr_type,list_status,list_date,is_hs"}

{"api_name": "ZTABLE_NAME", "token": "ZTOKEN","params": {ZPARAMS},"fields": "ZFields"}
*/


        public String buildRequestFieldStr() {
            String fieldListStr = "";
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < filedNameList.size(); i++) {
                String filedName = filedNameList.get(i);
                if (i == filedNameList.size() - 1) {
                    sb.append(filedName);
                } else {
                    sb.append(filedName + ",");
                }

            }
            fieldListStr = sb.toString();

            return fieldListStr;
        }


        public String buildRequestInputParamStr() {
            String paramListStr = "";
// {"list_stauts":"P"}

            String pre = "{";
            String end = "}";
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < inputParamList.size(); i++) {
                Table_Input_Param param = inputParamList.get(i);
                String paramField = param.paramName;
                String value = param.paramValue;
                String key2value = "\"" + paramField + "\":\"" + value.trim() + "\"";
                if (i == inputParamList.size() - 1) {
                    sb.append(key2value);
                } else {
                    sb.append(key2value + ",");
                }

            }
            paramListStr = pre + sb.toString() + end;

            return paramListStr;
        }

        // {"api_name": "ZTABLE_NAME", "token": "ZTOKEN","params": {ZPARAMS},"fields": "ZFields"}
        public String createRequestBody() {
            String requestBody = new String(requestTeplateStr);

            requestBody = requestBody.replace("ZTABLE_NAME", this.tableName);
            requestBody = requestBody.replace("ZTOKEN", Tushare_Token);
            String buildRequestParamStr = buildRequestInputParamStr();
            requestBody = requestBody.replace("ZPARAMS", buildRequestParamStr);
            String buildFieldStr = buildRequestFieldStr();
            requestBody = requestBody.replace("ZFields", buildFieldStr);

            return requestBody;

        }


        public String createRequestBodyWithTS_Code(String tsCode) {
            String requestBody = new String(requestTeplateStr);

            requestBody = requestBody.replace("ZTABLE_NAME", this.tableName);
            requestBody = requestBody.replace("ZTOKEN", Tushare_Token);
            String buildRequestParamStr = buildRequestInputParamStr();
            requestBody = requestBody.replace("ZPARAMS", buildRequestParamStr);
            String buildFieldStr = buildRequestFieldStr();
            requestBody = requestBody.replace("ZFields", buildFieldStr);

            //    if(requestBody.contains(""))
            String replacetsCode = "\"ts_code\":\"" + tsCode + "\"";
            requestBody = requestBody.replace("\"ts_code\":\"\"", replacetsCode);

            return requestBody;

        }


        public void addTableItem(Table_Item item) {
            item.ownnerTableName = this.tableName;
            this.fieldList.add(item);
            filedNameList.add(item.fieldName.trim());
        }

        public void addTableInputParam(Table_Input_Param param) {
            inputParamList.add(param);
        }


        public String getTableName() {
            return tableName;
        }

/*     CREATE TABLE "search_history"(
             [auto_id] INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL
,[user_id] INTEGER NOT NULL
,[query_txt] TEXT
,[gmt_search] INTEGER
);*/

        public String createTableSqlStr() {
            String createSqlStr = "";
            String Pre = "CREATE TABLE \"" + this.tableName + "\" (";
            String End = ");";
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < this.fieldList.size(); i++) {
                String str1 = ",[";
                if (i == 0) {
                    str1 = "[";
                }

                Table_Item tableItem = fieldList.get(i);
                String fieldName = tableItem.fieldName.trim();
                String str2 = fieldName;
                String str3 = "] ";
                SQLITE_TYPE itemType = tableItem.fieldType;
                String str4 = getTypeString(itemType);


                String str5 = "";
                boolean isAutoInc = tableItem.isAutoIncrement;
                if (isAutoInc && itemType == SQLITE_TYPE.INT) {
                    str5 = " AUTOINCREMENT ";
                }


                String str6 = "";
                boolean isUnique = tableItem.isUnique;
                if (isUnique) {
                    str6 = " UNIQUE ";
                }


                String str7 = "";
                boolean isContentNull = tableItem.isContentEmpty;
                if (isContentNull) {
                    str7 = " NOT NULL ";
                }

                String str8 = "";
                boolean isDefault = tableItem.isDefault;
                if (isDefault) {
                    String defaultValue = tableItem.defaultValue;
                    str8 = " default \"" + defaultValue.trim() + "\"";
                }
                String curItemStr = str1 + str2 + str3 + str4 + str5 + str6 + str7 + str8;
                sb.append(curItemStr);
            }

            // , Primary Key(dict_id,key_id)
            String primaryKey = "";
            for (int i = 0; i < this.fieldList.size(); i++) {
                Table_Item tableItem = fieldList.get(i);
                boolean isPrimaryKey = tableItem.isPrimaryKey;
                if (isPrimaryKey) {
                    if ("".equals(primaryKey)) {
                        primaryKey = " , Primary Key(" + tableItem.fieldName;
                    } else {
                        primaryKey = primaryKey + "," + tableItem.fieldName;
                    }

                }

            }
            if (!"".equals(primaryKey) && !primaryKey.trim().endsWith(")")) {
                primaryKey = primaryKey + ")";
            }
            createSqlStr = Pre + sb.toString() + primaryKey + End;
            return createSqlStr;
        }

        public void setTableName(String tableName) {
            this.tableName = tableName;
        }

        public ArrayList<Table_Item> getFieldList() {
            return fieldList;
        }

        public void setFieldList(ArrayList<Table_Item> fieldList) {
            this.fieldList = fieldList;
        }
    }


/*


CREATE TABLE "search_history"(
[auto_id] INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL
,[user_id] INTEGER NOT NULL
,[query_txt] TEXT
,[gmt_search] INTEGER
);

    CREATE TABLE "dict"(
[dict_id] INTEGER NOT NULL
,[key_id] INTEGER NOT NULL
,[int_value] INTEGER
,[txt_value] TEXT
,[key_desc] TEXT
, Primary Key(dict_id,key_id)     【双主键】     // 在最后创建
);
*/


    static class Table_Data {   // 一行的数据
        String tableName;
        ArrayList<String> fieldNameList;   // 表头
        // 当前行 filed---value  一一对应的类 value就是从 JSON获取到的数据  也是需要插入数据库的数据
        // field 唯一
        Map<String, String> fieldAndValueMap;

        Table_Data(ArrayList<String> mfieldNameList, String ctableName) {
            this.fieldNameList = mfieldNameList;
            this.tableName = ctableName;
            this.fieldAndValueMap = new LinkedHashMap<String, String>();
        }


        String buildInsertSql() {
            String insertSqlStr = "";
            String pre = "INSERT INTO " + this.tableName + "(";
            String middle_2 = ") VALUES (";
            String end = ");";
            String middle_1_FiledKey = "";
            String middle_3_FiledValue = "";

            Map.Entry<String, String> entry;

            if (fieldAndValueMap != null) {
                Iterator iterator = fieldAndValueMap.entrySet().iterator();
                while (iterator.hasNext()) {
                    entry = (Map.Entry<String, String>) iterator.next();
                    String key = "\"" + entry.getKey() + "\"";
                    String value = "\"" + entry.getValue() + "\"";
                    middle_1_FiledKey = middle_1_FiledKey + key + ",";  //Map的Value
                    middle_3_FiledValue = middle_3_FiledValue + value + ",";  //Map的Value
                }
            }
            middle_1_FiledKey = middle_1_FiledKey.trim();
            middle_3_FiledValue = middle_3_FiledValue.trim();

            while (middle_1_FiledKey.endsWith(",")) {
                middle_1_FiledKey = middle_1_FiledKey.substring(0, middle_1_FiledKey.length() - 1);
            }

            while (middle_3_FiledValue.endsWith(",")) {
                middle_3_FiledValue = middle_3_FiledValue.substring(0, middle_3_FiledValue.length() - 1);
            }


//            INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) VALUES (1, 'Paul', 32, 'California', 20000.00 );

            insertSqlStr = pre + middle_1_FiledKey + middle_2 + middle_3_FiledValue + end;

            return insertSqlStr;
        }

    }

//  建表的Common项

    static class Table_Input_Param {
        String paramName;
        String paramType;
        String paramValue;
        String desc;
        boolean isRankLimit; // 可选值是否是在一个范围
        boolean isMustParam;  // 是否是必要参数
        boolean isDateParam;
        String DateFormate;  //   日期的格式    如 YYYYMMDD
        String[] selectedRank;  // 如果是String  并且 isRankLimit = true   那么这就是它的范围

        Table_Input_Param(String cparamName, String cparamType, String caramValue) {
            this.paramName = cparamName;
            this.paramType = cparamType;
            this.paramValue = caramValue;
        }
    }


    static class Table_Item {
        String ownnerTableName;  // 所属表
        String chineseName;  // 中文名称
        String unitDesc;   // 单元  如 万元 亿元  人 万人 万手 .... 等  元  美元


        // 创表的属性=====Begin
        String fieldName;

        SQLITE_TYPE fieldType;
        boolean isPrimaryKey;  // 是否主键
        boolean isAutoIncrement;  // 是否自增
        boolean isContentEmpty;   // 是否可为空
        boolean isUnique; // 是否唯一
        boolean isDefault;  // 是否有默认值
        String defaultValue;   // 默认值
        boolean isForeignKey;   // 该项是否是外键
        Table_Item foreignKey;  // 外键位置
        // 创表的属性=====End

        Table_Item(String name, SQLITE_TYPE type, String mchineseName) {
            this.fieldName = name.trim();
            this.fieldType = type;
            this.chineseName = mchineseName;

        }


    }


    static String getTypeString(SQLITE_TYPE type) {
        String typeStr = "";
        switch (type) {  // an enum switch
            case INT:
                typeStr = "INTEGER";
                break;
            case REAL:
                typeStr = "REAL";
                break;
            case TEXT:
                typeStr = "TEXT";
                break;
            default:
                typeStr = "TEXT";
        }
        return typeStr;
    }

    static enum SQLITE_TYPE {
        INT,
        TEXT,
        REAL
    }

    ;

    // 类型 INTEGER
    // TEXT
    // long     一般是时间
    // FLOAT 小数

    // 创建表


}