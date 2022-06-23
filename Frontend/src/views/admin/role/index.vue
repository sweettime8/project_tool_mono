<template>
  <Layout >
    <div class="app-container claim">
     
      <div class="filter-container">
          <el-row :gutter="20" >
              <el-col :span="6" :offset="1">
                <el-row >
                 <h2 class="margin-top">NHÓM QUYỀN</h2>
                 <template>
                   <el-select v-model="roleCodeSelect.roleCode" value-key="roleName">
                        <el-option v-for="item in list_role" :key="item.id" :label="item.roleCode" :value="item.roleCode"  :disabled="item.roleCode=='Administrator'">     
                       </el-option>
                     </el-select>
  
                </template>
                </el-row>
                <el-row >
                  <br/><br/>
                    <el-button class="search" @click="addRole()" type="primary">Thêm</el-button>
                    <el-button v-model="roleCodeSelect.roleCode" class="search" @click="deleteRolePathPerMis()" type="primary">Xóa</el-button>
                </el-row>
  
              </el-col>
              <el-col :span="10"  style="margin-left:0">
                   <h2 class="margin-top">CHỨC NĂNG</h2>
                  <el-form ref="RolePathForm" :model="rolePagePermission" :rules="rules" label-position="left" label-width="180px">
                      <el-form-item label="Tên nhóm quyền">
                          <el-input placeholder="Tên nhóm quyền" :maxlength="100" v-model="roleCodeSelect.roleCode"> </el-input>
                      </el-form-item>   
                      <el-form-item label="Mô tả nhóm quyền">
                          <el-input placeholder="Mô tả nhóm quyền" :maxlength="255" v-model="roleCodeSelect.roleDetail"> </el-input>
                      </el-form-item> 
                      <div class="body-table" style="overflow: auto; ">
                      <el-table  v-loading="loading" :data="list_apiPath" border fit highlight-current-row style="width: 100%" >
                          <el-table-column align="center" v-if="!list_apiPath || !list_apiPath.length">
                              <span>Không có bản ghi</span>
                          </el-table-column>
  
                          <el-table-column align="center" label="STT" width="50">
                              <template slot-scope = "scope">
                                  <span>{{ scope.$index + 1 }}</span>
                              </template>
                          </el-table-column>
  
                          <el-table-column label="Quyền">
                              <template slot-scope="scope">
                                  <span>{{ scope.row.pageName }}</span>
                              </template>
                           </el-table-column>
  
                           <el-table-column label="Thêm" width="70">
                              <template slot-scope="scope">
                                   <el-checkbox :disabled="list_apiPath_FirstLoad[scope.$index].canCreate == 0" :value="scope.row.canCreate == 1" :checked="scope.row.canCreate == 1 && scope.row.roleCode != roleUserLogin" @change="updateDataCheckbox('CREATE',scope.row, $event)"></el-checkbox>
                              </template>
                           </el-table-column>
                           <el-table-column label="Sửa" width="70">
                              <template slot-scope="scope">
                                   <el-checkbox :disabled="list_apiPath_FirstLoad[scope.$index].canUpdate == 0" :value="scope.row.canUpdate == 1 " :checked="scope.row.canUpdate == 1 && scope.row.roleCode != roleUserLogin" @change="updateDataCheckbox('UPDATE',scope.row, $event)"></el-checkbox>
                              </template>
                           </el-table-column>
                           <el-table-column label="Xem" width="70">
                              <template slot-scope="scope">
                                   <el-checkbox :disabled="list_apiPath_FirstLoad[scope.$index].canRead == 0" :value="scope.row.canRead == 1"  :checked="scope.row.canRead == 1 && scope.row.roleCode != roleUserLogin" @change="updateDataCheckbox('READ',scope.row, $event)"></el-checkbox>
                              </template>
                           </el-table-column>
                           <el-table-column label="Xóa" width="70">
                              <template slot-scope="scope">
                                   <el-checkbox :disabled="list_apiPath_FirstLoad[scope.$index].canDelete == 0" :value="scope.row.canDelete == 1" :checked="scope.row.canDelete == 1 && scope.row.roleCode != roleUserLogin" @change="updateDataCheckbox('DELETE',scope.row, $event)"  ></el-checkbox>
                              </template>
                           </el-table-column>                                                                                                   
  
                       </el-table>
                       </div>
                       <br/><br/>
                    <el-button class="search" @click="createPermission()" type="primary">Đồng ý</el-button>
                    <el-button class="search" @click="clearForm()" type="primary">Làm lại</el-button>
                  </el-form>       
              </el-col>
          </el-row>
  
      </div>
      
    </div>   
  </Layout>
</template>





<script>
import { layoutMethods, layoutComputed } from "@/state/helpers";
import Layout from "@/layouts/main";
import Resource from "@/common/api.js";
const roleCreateByUserResource = new Resource("role/get-role-create-by-user");

export default {
  components: {
    Layout,
  },
  data() {
    return {
      dialogVisible: false,
      addRoleCode: "",
      action :'INSERT_RULE',
      roleCodeSelect: {
         roleCode : "",
         roleName : "",
         roleDetail:""
      },
      newRole : "",
      roleUserLogin:"",
      roleUser: {
         roleCode : "",
         roleName : "",
      },
      default_role: {
        id: "",
        roleCode: "",
        roleName: "Chọn quyền",
        isAdmin: 0,
        isDelete: 0,
        createdAt: "",
      },
      rolePagePermission:{
          roleName:'',
          roleCode:'',
          pageUrl:'',
          pageName:'',
          canCreate: 0,
          canRead: 0,
          canUpdate : 0,
          canDelete : 0,
          isDelete : 0
      },
      addPagePermission:{
          roleName:'',
          roleCode:'',
          roleDetail:'',
          roleCodeOld:'',
          action:'',
          lstApiAddRole:null

      },
      list_role: null,
      list_apiPath: null,
      list_apiPath_FirstLoad: null,
      list_apiPathSelect: null,
      list_user: null,
      list: null,
      total: 0,
      loading: true,
      dialogLoading: false,
      dialogEdit: false,
      dialogCreate: false,
      reason: null,
      uuid: null,
      id: null,
      searchKeyword: null,
      selectUser: null,
      query: {
        currentPage: 1,
        rowsPerPage: 20,
        sort: "createdAt",
        keyword: this.searchKeyword,
        roleCode: this.roleCode,
      },

      rules: {
        selectUser: [
          {
            required: true,
            message: "Bắt buộc nhập user",
            trigger: "blur",
          },
        ],
        addRoleCode: [
          {
            required: true,
            message: "Bắt buộc nhập chọn quyền",
            trigger: "blur",
          },
        ],
      },
      attribute: {
        name: "",
        values: [
          {
            value: "",
          },
        ],
      }
    };
  },
 computed: {

  },
  created() {
    this.changeLayout();
    this.getRoleListCreateByUserLogin();
    this.getListPageByRoleUserLogin();
  },
  
  methods: {
      ...layoutMethods,
    async changeLayout(){
      this.changeLayoutType({
        layoutType: 'vertical',
      }); 
    },

    async getRoleListCreateByUserLogin(){
        console.log("[role]_getRoleListCreateByUserLogin : ");
        const { data } = await roleCreateByUserResource.list();
        this.list_role = data;
        this.userNameLogin = this.$store.getters.userName;
    },

    async getListPageByRoleUserLogin(){
      console.log("[role]_getListPageByRoleUserLogin : ");
    
    }


  },
};
</script>

<style lang="scss" scoped>
.margin-top {
  margin-top: 40px;
}
.search {
  margin-left: 15px;
}
.block {
  display: inline-block;
  //float: left;
  margin-right: 20px;
}
.blue {
  color: green;
}
.red {
  color: red;
}
.add-value {
  text-align: right;
}
.list-detail .item {
  margin-bottom: 10px;
  .label {
    display: inline-block;
    width: 100px;
  }
  img {
    max-width: 100%;
    display: inline-block;
  }
}

.guruInfor
{
  margin: 7px;
}

</style>
<style lang="scss">
.form-value {
  .el-input__inner {
    margin-bottom: 10px;
  }
}
.claim td .el-button {
  margin-left: 0;
}
</style>
