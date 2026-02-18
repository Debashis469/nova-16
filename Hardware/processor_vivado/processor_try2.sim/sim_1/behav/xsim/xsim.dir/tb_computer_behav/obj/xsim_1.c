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
IKI_DLLESPEC extern void execute_6658(char*, char *);
IKI_DLLESPEC extern void execute_6659(char*, char *);
IKI_DLLESPEC extern void execute_6660(char*, char *);
IKI_DLLESPEC extern void execute_9810(char*, char *);
IKI_DLLESPEC extern void execute_9811(char*, char *);
IKI_DLLESPEC extern void execute_9812(char*, char *);
IKI_DLLESPEC extern void execute_9813(char*, char *);
IKI_DLLESPEC extern void execute_9814(char*, char *);
IKI_DLLESPEC extern void execute_9815(char*, char *);
IKI_DLLESPEC extern void execute_9816(char*, char *);
IKI_DLLESPEC extern void execute_9817(char*, char *);
IKI_DLLESPEC extern void execute_5(char*, char *);
IKI_DLLESPEC extern void execute_6666(char*, char *);
IKI_DLLESPEC extern void execute_6962(char*, char *);
IKI_DLLESPEC extern void vlog_simple_process_execute_0_fast_no_reg_no_agg(char*, char*, char*);
IKI_DLLESPEC extern void vlog_const_rhs_process_execute_0_fast_no_reg_no_agg(char*, char*, char*);
IKI_DLLESPEC extern void execute_6667(char*, char *);
IKI_DLLESPEC extern void execute_6811(char*, char *);
IKI_DLLESPEC extern void execute_334(char*, char *);
IKI_DLLESPEC extern void execute_8502(char*, char *);
IKI_DLLESPEC extern void execute_8503(char*, char *);
IKI_DLLESPEC extern void execute_8504(char*, char *);
IKI_DLLESPEC extern void execute_8551(char*, char *);
IKI_DLLESPEC extern void vlog_simple_process_execute_0_fast_no_reg(char*, char*, char*);
IKI_DLLESPEC extern void execute_9520(char*, char *);
IKI_DLLESPEC extern void execute_8974(char*, char *);
IKI_DLLESPEC extern void execute_9806(char*, char *);
IKI_DLLESPEC extern void execute_9807(char*, char *);
IKI_DLLESPEC extern void execute_9808(char*, char *);
IKI_DLLESPEC extern void execute_9809(char*, char *);
IKI_DLLESPEC extern void execute_6096(char*, char *);
IKI_DLLESPEC extern void execute_9533(char*, char *);
IKI_DLLESPEC extern void execute_6098(char*, char *);
IKI_DLLESPEC extern void execute_6099(char*, char *);
IKI_DLLESPEC extern void execute_6101(char*, char *);
IKI_DLLESPEC extern void execute_6102(char*, char *);
IKI_DLLESPEC extern void execute_6103(char*, char *);
IKI_DLLESPEC extern void execute_9534(char*, char *);
IKI_DLLESPEC extern void execute_9535(char*, char *);
IKI_DLLESPEC extern void execute_9536(char*, char *);
IKI_DLLESPEC extern void execute_6652(char*, char *);
IKI_DLLESPEC extern void execute_6653(char*, char *);
IKI_DLLESPEC extern void execute_6654(char*, char *);
IKI_DLLESPEC extern void execute_6655(char*, char *);
IKI_DLLESPEC extern void execute_6656(char*, char *);
IKI_DLLESPEC extern void execute_6657(char*, char *);
IKI_DLLESPEC extern void execute_9795(char*, char *);
IKI_DLLESPEC extern void execute_9796(char*, char *);
IKI_DLLESPEC extern void execute_9797(char*, char *);
IKI_DLLESPEC extern void execute_9798(char*, char *);
IKI_DLLESPEC extern void execute_9803(char*, char *);
IKI_DLLESPEC extern void execute_9804(char*, char *);
IKI_DLLESPEC extern void execute_6662(char*, char *);
IKI_DLLESPEC extern void execute_6663(char*, char *);
IKI_DLLESPEC extern void execute_6664(char*, char *);
IKI_DLLESPEC extern void execute_6665(char*, char *);
IKI_DLLESPEC extern void execute_9818(char*, char *);
IKI_DLLESPEC extern void execute_9819(char*, char *);
IKI_DLLESPEC extern void execute_9820(char*, char *);
IKI_DLLESPEC extern void execute_9821(char*, char *);
IKI_DLLESPEC extern void execute_9822(char*, char *);
IKI_DLLESPEC extern void execute_9823(char*, char *);
IKI_DLLESPEC extern void vlog_transfunc_eventcallback(char*, char*, unsigned, unsigned, unsigned, char *);
IKI_DLLESPEC extern void transaction_19(char*, char*, unsigned, unsigned, unsigned);
IKI_DLLESPEC extern void transaction_25(char*, char*, unsigned, unsigned, unsigned);
funcp funcTab[66] = {(funcp)execute_2, (funcp)execute_6658, (funcp)execute_6659, (funcp)execute_6660, (funcp)execute_9810, (funcp)execute_9811, (funcp)execute_9812, (funcp)execute_9813, (funcp)execute_9814, (funcp)execute_9815, (funcp)execute_9816, (funcp)execute_9817, (funcp)execute_5, (funcp)execute_6666, (funcp)execute_6962, (funcp)vlog_simple_process_execute_0_fast_no_reg_no_agg, (funcp)vlog_const_rhs_process_execute_0_fast_no_reg_no_agg, (funcp)execute_6667, (funcp)execute_6811, (funcp)execute_334, (funcp)execute_8502, (funcp)execute_8503, (funcp)execute_8504, (funcp)execute_8551, (funcp)vlog_simple_process_execute_0_fast_no_reg, (funcp)execute_9520, (funcp)execute_8974, (funcp)execute_9806, (funcp)execute_9807, (funcp)execute_9808, (funcp)execute_9809, (funcp)execute_6096, (funcp)execute_9533, (funcp)execute_6098, (funcp)execute_6099, (funcp)execute_6101, (funcp)execute_6102, (funcp)execute_6103, (funcp)execute_9534, (funcp)execute_9535, (funcp)execute_9536, (funcp)execute_6652, (funcp)execute_6653, (funcp)execute_6654, (funcp)execute_6655, (funcp)execute_6656, (funcp)execute_6657, (funcp)execute_9795, (funcp)execute_9796, (funcp)execute_9797, (funcp)execute_9798, (funcp)execute_9803, (funcp)execute_9804, (funcp)execute_6662, (funcp)execute_6663, (funcp)execute_6664, (funcp)execute_6665, (funcp)execute_9818, (funcp)execute_9819, (funcp)execute_9820, (funcp)execute_9821, (funcp)execute_9822, (funcp)execute_9823, (funcp)vlog_transfunc_eventcallback, (funcp)transaction_19, (funcp)transaction_25};
const int NumRelocateId= 66;

void relocate(char *dp)
{
	iki_relocate(dp, "xsim.dir/tb_computer_behav/xsim.reloc",  (void **)funcTab, 66);

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
