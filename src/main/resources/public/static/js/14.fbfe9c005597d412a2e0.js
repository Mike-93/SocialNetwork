webpackJsonp([14],{HW6K:function(s,e,a){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var r=a("Dd8w"),t=a.n(r),o=a("+cKO"),n=a("TYx6"),d=a("i53X"),i=a("NYxO"),c={name:"ChangePassword",components:{PasswordField:n.a,PasswordRepeatField:d.a},data:function(){return{password:"",passwordTwo:""}},methods:t()({},Object(i.b)("profile/account",["passwordSet"]),{submitHandler:function(){var s=this;this.$v.$invalid?this.$v.$touch():this.passwordSet(this.passwordTwo).then(function(){s.$router.push({name:"ChangePasswordSuccess"})})}}),validations:{password:{required:o.required,minLength:Object(o.minLength)(8)},passwordTwo:{required:o.required,sameAsPassword:Object(o.sameAs)("password")}}},w={render:function(){var s=this,e=s.$createElement,a=s._self._c||e;return a("div",{staticClass:"change-password"},[a("h2",{staticClass:"change-password__title form__title"},[s._v("Новый пароль")]),a("form",{staticClass:"change-password__form",on:{submit:function(e){return e.preventDefault(),s.submitHandler(e)}}},[a("password-field",{class:{checked:s.$v.password.required&&s.$v.passwordTwo.sameAsPassword},attrs:{id:"change-password",v:s.$v.password,info:"info",registration:"registration"},model:{value:s.password,callback:function(e){s.password=e},expression:"password"}}),a("password-repeat-field",{class:{checked:s.$v.password.required&&s.$v.passwordTwo.sameAsPassword},attrs:{id:"change-repeat-password",v:s.$v.passwordTwo},model:{value:s.passwordTwo,callback:function(e){s.passwordTwo=e},expression:"passwordTwo"}}),a("div",{staticClass:"change-password__action"},[a("button-hover",{attrs:{tag:"button",type:"submit",variant:"white"}},[s._v("Отправить")])],1)],1)])},staticRenderFns:[]};var p=a("VU/8")(c,w,!1,function(s){a("pe/3")},null,null);e.default=p.exports},"pe/3":function(s,e){}});
//# sourceMappingURL=14.fbfe9c005597d412a2e0.js.map