#
# This script is shared between ss deploy and undeploy scripts
#
EPS_HOME=/opt/ericsson/eps/eps
ROOT_EPS_DEPLOY_FOLDER=/var/ericsson/eps/flows
eps_user_name=epsadmin

#solution set folder
SS_HOME=/opt/ericsson/MockSS

eps_services=""

#one eps instance
eps_services[0]="mock_eps00"

echo "eps services are ${eps_services[@]:0} "

flow_files[1]="" 

echo "there are ${#flow_files[@]} xml flows definitions"



jar_file_list=""
