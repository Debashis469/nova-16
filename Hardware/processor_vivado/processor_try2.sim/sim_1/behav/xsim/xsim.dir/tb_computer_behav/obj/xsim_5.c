/**********************************************************************/
/*   ____  ____                                                       */
/*  /   /\/   /                                                       */
/* /___/  \  /                                                        */
/* \   \   \/                                                         */
/*  \   \        Copyright (c) 2003-2020 Xilinx, Inc.                 */
/*  /   /        All Right Reserved.                                  */
/* /---/   /\                                                         */
/* \   \  /  \                                                        */
/*  \___\/\___\                                                       */
/**********************************************************************/

#if defined(_WIN32)
 #include "stdio.h"
 #define IKI_DLLESPEC __declspec(dllimport)
#else
 #define IKI_DLLESPEC
#endif
#include "iki.h"
#include <string.h>
#include <math.h>
#ifdef __GNUC__
#include <stdlib.h>
#else
#include <malloc.h>
#define alloca _alloca
#endif
/**********************************************************************/
/*   ____  ____                                                       */
/*  /   /\/   /                                                       */
/* /___/  \  /                                                        */
/* \   \   \/                                                         */
/*  \   \        Copyright (c) 2003-2020 Xilinx, Inc.                 */
/*  /   /        All Right Reserved.                                  */
/* /---/   /\                                                         */
/* \   \  /  \                                                        */
/*  \___\/\___\                                                       */
/**********************************************************************/

#if defined(_WIN32)
 #include "stdio.h"
 #define IKI_DLLESPEC __declspec(dllimport)
#else
 #define IKI_DLLESPEC
#endif
#include "iki.h"
#include <string.h>
#include <math.h>
#ifdef __GNUC__
#include <stdlib.h>
#else
#include <malloc.h>
#define alloca _alloca
#endif
typedef void (*funcp)(char *, char *);
extern int main(int, char**);
IKI_DLLESPEC extern void execute_2(char*, char *);
IKI_DLLESPEC extern void execute_6859(char*, char *);
IKI_DLLESPEC extern void execute_6860(char*, char *);
IKI_DLLESPEC extern void execute_6861(char*, char *);
IKI_DLLESPEC extern void execute_10011(char*, char *);
IKI_DLLESPEC extern void execute_10012(char*, char *);
IKI_DLLESPEC extern void execute_10013(char*, char *);
IKI_DLLESPEC extern void execute_10014(char*, char *);
IKI_DLLESPEC extern void execute_10015(char*, char *);
IKI_DLLESPEC extern void execute_10016(char*, char *);
IKI_DLLESPEC extern void execute_10017(char*, char *);
IKI_DLLESPEC extern void execute_10018(char*, char *);
IKI_DLLESPEC extern void execute_10010(char*, char *);
IKI_DLLESPEC extern void execute_5(char*, char *);
IKI_DLLESPEC extern void execute_6(char*, char *);
IKI_DLLESPEC extern void execute_7162(char*, char *);
IKI_DLLESPEC extern void vlog_simple_process_execute_0_fast_no_reg_no_agg(char*, char*, char*);
IKI_DLLESPEC extern void vlog_const_rhs_process_execute_0_fast_no_reg_no_agg(char*, char*, char*);
IKI_DLLESPEC extern void execute_6867(char*, char *);
IKI_DLLESPEC extern void execute_7011(char*, char *);
IKI_DLLESPEC extern void execute_535(char*, char *);
IKI_DLLESPEC extern void execute_8702(char*, char *);
IKI_DLLESPEC extern void execute_8703(char*, char *);
IKI_DLLESPEC extern void execute_8704(char*, char *);
IKI_DLLESPEC extern void execute_8751(char*, char *);
IKI_DLLESPEC extern void vlog_simple_process_execute_0_fast_no_reg(char*, char*, char*);
IKI_DLLESPEC extern void execute_9720(char*, char *);
IKI_DLLESPEC extern void execute_9174(char*, char *);
IKI_DLLESPEC extern void execute_10006(char*, char *);
IKI_DLLESPEC extern void execute_10007(char*, char *);
IKI_DLLESPEC extern void execute_10008(char*, char *);
IKI_DLLESPEC extern void execute_10009(char*, char *);
IKI_DLLESPEC extern void execute_6297(char*, char *);
IKI_DLLESPEC extern void execute_9733(char*, char *);
IKI_DLLESPEC extern void execute_6299(char*, char *);
IKI_DLLESPEC extern void execute_6300(char*, char *);
IKI_DLLESPEC extern void execute_6302(char*, char *);
IKI_DLLESPEC extern void execute_6303(char*, char *);
IKI_DLLESPEC extern void execute_6304(char*, char *);
IKI_DLLESPEC extern void execute_9734(char*, char *);
IKI_DLLESPEC extern void execute_9735(char*, char *);
IKI_DLLESPEC extern void execute_9736(char*, char *);
IKI_DLLESPEC extern void execute_6853(char*, char *);
IKI_DLLESPEC extern void execute_6854(char*, char *);
IKI_DLLESPEC extern void execute_6855(char*, char *);
IKI_DLLESPEC extern void execute_6856(char*, char *);
IKI_DLLESPEC extern void execute_6857(char*, char *);
IKI_DLLESPEC extern void execute_6858(char*, char *);
IKI_DLLESPEC extern void execute_9995(char*, char *);
IKI_DLLESPEC extern void execute_9996(char*, char *);
IKI_DLLESPEC extern void execute_9997(char*, char *);
IKI_DLLESPEC extern void execute_9998(char*, char *);
IKI_DLLESPEC extern void execute_10003(char*, char *);
IKI_DLLESPEC extern void execute_10004(char*, char *);
IKI_DLLESPEC extern void execute_6863(char*, char *);
IKI_DLLESPEC extern void execute_6864(char*, char *);
IKI_DLLESPEC extern void execute_6865(char*, char *);
IKI_DLLESPEC extern void execute_6866(char*, char *);
IKI_DLLESPEC extern void execute_10019(char*, char *);
IKI_DLLESPEC extern void execute_10020(char*, char *);
IKI_DLLESPEC extern void execute_10021(char*, char *);
IKI_DLLESPEC extern void execute_10022(char*, char *);
IKI_DLLESPEC extern void execute_10023(char*, char *);
IKI_DLLESPEC extern void execute_10024(char*, char *);
IKI_DLLESPEC extern void vlog_transfunc_eventcallback(char*, char*, unsigned, unsigned, unsigned, char *);
IKI_DLLESPEC extern void transaction_19(char*, char*, unsigned, unsigned, unsigned);
IKI_DLLESPEC extern void transaction_25(char*, char*, unsigned, unsigned, unsigned);
funcp funcTab[67] = {(funcp)execute_2, (funcp)execute_6859, (funcp)execute_6860, (funcp)execute_6861, (funcp)execute_10011, (funcp)execute_10012, (funcp)execute_10013, (funcp)execute_10014, (funcp)execute_10015, (funcp)execute_10016, (funcp)execute_10017, (funcp)execute_10018, (funcp)execute_10010, (funcp)execute_5, (funcp)execute_6, (funcp)execute_7162, (funcp)vlog_simple_process_execute_0_fast_no_reg_no_agg, (funcp)vlog_const_rhs_process_execute_0_fast_no_reg_no_agg, (funcp)execute_6867, (funcp)execute_7011, (funcp)execute_535, (funcp)execute_8702, (funcp)execute_8703, (funcp)execute_8704, (funcp)execute_8751, (funcp)vlog_simple_process_execute_0_fast_no_reg, (funcp)execute_9720, (funcp)execute_9174, (funcp)execute_10006, (funcp)execute_10007, (funcp)execute_10008, (funcp)execute_10009, (funcp)execute_6297, (funcp)execute_9733, (funcp)execute_6299, (funcp)execute_6300, (funcp)execute_6302, (funcp)execute_6303, (funcp)execute_6304, (funcp)execute_9734, (funcp)execute_9735, (funcp)execute_9736, (funcp)execute_6853, (funcp)execute_6854, (funcp)execute_6855, (funcp)execute_6856, (funcp)execute_6857, (funcp)execute_6858, (funcp)execute_9995, (funcp)execute_9996, (funcp)execute_9997, (funcp)execute_9998, (funcp)execute_10003, (funcp)execute_10004, (funcp)execute_6863, (funcp)execute_6864, (funcp)execute_6865, (funcp)execute_6866, (funcp)execute_10019, (funcp)execute_10020, (funcp)execute_10021, (funcp)execute_10022, (funcp)execute_10023, (funcp)execute_10024, (funcp)vlog_transfunc_eventcallback, (funcp)transaction_19, (funcp)transaction_25};
const int NumRelocateId= 67;

void relocate(char *dp)
{
	iki_relocate(dp, "xsim.dir/tb_computer_behav/xsim.reloc",  (void **)funcTab, 67);

	/*Populate the transaction function pointer field in the whole net structure */
}

void sensitize(char *dp)
{
	iki_sensitize(dp, "xsim.dir/tb_computer_behav/xsim.reloc");
}

	// Initialize Verilog nets in mixed simulation, for the cases when the value at time 0 should be propagated from the mixed language Vhdl net

void wrapper_func_0(char *dp)

{

}

void simulate(char *dp)
{
		iki_schedule_processes_at_time_zero(dp, "xsim.dir/tb_computer_behav/xsim.reloc");
	wrapper_func_0(dp);

	iki_execute_processes();

	// Schedule resolution functions for the multiply driven Verilog nets that have strength
	// Schedule transaction functions for the singly driven Verilog nets that have strength

}
#include "iki_bridge.h"
void relocate(char *);

void sensitize(char *);

void simulate(char *);

extern SYSTEMCLIB_IMP_DLLSPEC void local_register_implicit_channel(int, char*);
extern SYSTEMCLIB_IMP_DLLSPEC int xsim_argc_copy ;
extern SYSTEMCLIB_IMP_DLLSPEC char** xsim_argv_copy ;

int main(int argc, char **argv)
{
    iki_heap_initialize("ms", "isimmm", 0, 2147483648) ;
    iki_set_xsimdir_location_if_remapped(argc, argv)  ;
    iki_set_sv_type_file_path_name("xsim.dir/tb_computer_behav/xsim.svtype");
    iki_set_crvs_dump_file_path_name("xsim.dir/tb_computer_behav/xsim.crvsdump");
    void* design_handle = iki_create_design("xsim.dir/tb_computer_behav/xsim.mem", (void *)relocate, (void *)sensitize, (void *)simulate, (void*)0, 0, isimBridge_getWdbWriter(), 0, argc, argv);
     iki_set_rc_trial_count(100);
    (void) design_handle;
    return iki_simulate_design();
}
