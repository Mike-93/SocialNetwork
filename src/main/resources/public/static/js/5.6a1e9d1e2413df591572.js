webpackJsonp([5],{ISIH:function(e,t,s){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var i=s("Dd8w"),a=s.n(i),r=s("NYxO"),n=s("CqtB"),l=s("+cKO"),o={name:"FriendsSearch",components:{NameField:s("znC5").a},data:function(){return{first_name:"",last_name:"",age_from:null,age_to:null,country_id:null,city_id:null,offset:0,itemPerPage:20,submitStatus:null}},computed:a()({},Object(r.c)("platform/countries",["getCountries"]),Object(r.c)("platform/cities",["getCities","getDefaultCities"])),methods:a()({},Object(r.b)("global/search",["searchUsers","clearSearch"]),Object(r.b)("platform/countries",["apiCountries"]),Object(r.d)("platform/cities",["setDefaultCities"]),{onSearchUsers:function(){if(this.$v.$invalid)return this.$v.$touch(),void(this.submitStatus="ERROR");this.submitStatus="OK";var e=this.first_name,t=this.last_name,s=this.age_from,i=this.age_to,a=this.country_id,r=this.city_id;this.searchUsers({first_name:e,last_name:t,age_from:s,age_to:i,country_id:a,city_id:r})}}),validations:{first_name:{required:l.required,minLength:Object(l.minLength)(3)},last_name:{required:l.required,minLength:Object(l.minLength)(3)}},beforeDestroy:function(){this.clearSearch()},created:function(){this.apiCountries()}},c={render:function(){var e=this,t=e.$createElement,s=e._self._c||t;return s("form",{staticClass:"friends-possible",attrs:{action:"#"},on:{submit:function(t){return t.preventDefault(),e.onSearchUsers(t)}}},[s("h4",{staticClass:"friends-possible__title"},[e._v("Параметры поиска")]),s("div",{staticClass:"friends-search"},[s("div",{staticClass:"friends-search__row"},[s("div",{staticClass:"friends-search__block"},[s("label",{staticClass:"search__label",attrs:{for:"friends-search-name"}},[e._v("Имя:")]),s("input",{directives:[{name:"model",rawName:"v-model.trim",value:e.$v.first_name.$model,expression:"$v.first_name.$model",modifiers:{trim:!0}}],staticClass:"search__input",attrs:{type:"text",id:"friends-search-name"},domProps:{value:e.$v.first_name.$model},on:{input:function(t){t.target.composing||e.$set(e.$v.first_name,"$model",t.target.value.trim())},blur:function(t){return e.$forceUpdate()}}})]),e.$v.first_name.minLength?e._e():s("div",{staticClass:"error"},[e._v("минимум "+e._s(e.$v.first_name.$params.minLength.min)+" символа.")]),s("div",{staticClass:"friends-search__block"},[s("label",{staticClass:"search__label",attrs:{for:"friends-search-lastname"}},[e._v("Фамилия:")]),s("input",{directives:[{name:"model",rawName:"v-model.trim",value:e.$v.last_name.$model,expression:"$v.last_name.$model",modifiers:{trim:!0}}],staticClass:"search__input",attrs:{type:"text",id:"friends-search-lastname"},domProps:{value:e.$v.last_name.$model},on:{input:function(t){t.target.composing||e.$set(e.$v.last_name,"$model",t.target.value.trim())},blur:function(t){return e.$forceUpdate()}}})]),e.$v.last_name.minLength?e._e():s("div",{staticClass:"error"},[e._v("минимум "+e._s(e.$v.last_name.$params.minLength.min)+" символа.")])]),s("div",{staticClass:"friends-search__block"},[s("label",{staticClass:"search__label"},[e._v("Возраст:")]),s("div",{staticClass:"search__row"},[s("select",{directives:[{name:"model",rawName:"v-model.number",value:e.age_from,expression:"age_from",modifiers:{number:!0}}],staticClass:"select friends-search__select",on:{change:function(t){var s=Array.prototype.filter.call(t.target.options,function(e){return e.selected}).map(function(t){var s="_value"in t?t._value:t.value;return e._n(s)});e.age_from=t.target.multiple?s:s[0]}}},[s("option",{attrs:{value:"null",disabled:"disabled"}},[e._v("От")]),s("option",{attrs:{value:"18"}},[e._v("От 18")]),s("option",{attrs:{value:"30"}},[e._v("От 30")]),s("option",{attrs:{value:""}},[e._v("Все")])]),s("span",{staticClass:"search__age-defis"},[e._v("—")]),s("select",{directives:[{name:"model",rawName:"v-model.number",value:e.age_to,expression:"age_to",modifiers:{number:!0}}],staticClass:"select friends-search__select",on:{change:function(t){var s=Array.prototype.filter.call(t.target.options,function(e){return e.selected}).map(function(t){var s="_value"in t?t._value:t.value;return e._n(s)});e.age_to=t.target.multiple?s:s[0]}}},[s("option",{attrs:{value:"null",disabled:"disabled"}},[e._v("До")]),s("option",{attrs:{value:"45"}},[e._v("До 50")]),s("option",{attrs:{value:""}},[e._v("Все")])])])]),s("div",{staticClass:"friends-search__block"},[s("label",{staticClass:"search__label"},[e._v("Регион:")]),s("div",{staticClass:"search__row"},[s("select",{directives:[{name:"model",rawName:"v-model",value:e.country_id,expression:"country_id"}],staticClass:"select friends-search__select",on:{change:function(t){var s=Array.prototype.filter.call(t.target.options,function(e){return e.selected}).map(function(e){return"_value"in e?e._value:e.value});e.country_id=t.target.multiple?s:s[0]}}},[s("option",{attrs:{value:"null",disabled:"disabled"}},[e._v("Страна")]),e._l(this.getCountries,function(t){return s("option",{key:t.id,domProps:{value:t.id}},[e._v(e._s(t.title))])}),s("option",{attrs:{value:""}},[e._v("Все")])],2),s("select",{directives:[{name:"model",rawName:"v-model",value:e.city_id,expression:"city_id"}],staticClass:"select friends-search__select",on:{change:function(t){var s=Array.prototype.filter.call(t.target.options,function(e){return e.selected}).map(function(e){return"_value"in e?e._value:e.value});e.city_id=t.target.multiple?s:s[0]}}},[s("option",{attrs:{value:"null",disabled:"disabled"}},[e._v("Город")]),e._l(this.getCities,function(t){return s("option",{key:t.id,domProps:{value:t.id}},[e._v(e._s(t.title))])}),s("option",{attrs:{value:""}},[e._v("Все")])],2)])])]),s("button",{staticClass:"friends-possible__btn",attrs:{type:"submit",disabled:!(e.$v.last_name.required&&e.$v.first_name.required&&e.$v.last_name.minLength&&e.$v.first_name.minLength)}},[s("simple-svg",{attrs:{filepath:"/static/img/search.svg"}}),s("span",{staticClass:"friends-possible__link"},[e._v("Искать друзей")])],1)])},staticRenderFns:[]};var d=s("VU/8")(o,c,!1,function(e){s("MPOU")},null,null).exports,u={name:"FriendsFind",components:{FriendsBlock:n.a,FriendsSearch:d},data:function(){return{isPossible:!0,friends:[]}},computed:a()({},Object(r.c)("profile/friends",["getResultById"]),{possibleFriends:function(){return this.getResultById("recommendations")},searchUsers:function(){return this.$store.getters["global/search/getResultById"]("users")}}),methods:a()({},Object(r.b)("profile/friends",["apiAddFriends","apiRecommendations"])),mounted:function(){0===this.possibleFriends.length&&this.apiRecommendations()}},_={render:function(){var e=this,t=e.$createElement,s=e._self._c||t;return s("div",{staticClass:"friends friends-find inner-page"},[s("div",{staticClass:"inner-page__main"},[s("div",{staticClass:"friends__header"},[s("h2",{staticClass:"friends__title"},[0===e.searchUsers.length?[e._v("Возможные друзья")]:[e._v("Найдено "+e._s(e.searchUsers.length)+" человек")]],2)]),e.searchUsers.length>0?s("div",{staticClass:"friends__list"},e._l(e.searchUsers,function(e){return s("friends-block",{key:e.id,attrs:{info:e}})}),1):e.possibleFriends?s("div",{staticClass:"friends__list"},e._l(e.possibleFriends,function(e){return s("friends-block",{key:e.id,attrs:{info:e}})}),1):e._e()]),s("div",{staticClass:"inner-page__aside"},[s("friends-search")],1)])},staticRenderFns:[]};var m=s("VU/8")(u,_,!1,function(e){s("J1tO")},null,null);t.default=m.exports},J1tO:function(e,t){},MPOU:function(e,t){},znC5:function(e,t,s){"use strict";var i={name:"EmailField",props:{value:{type:String,default:""},v:{type:Object,required:!0},label:{type:String,default:"Имя"},id:{type:String,required:!0}},computed:{name:{get:function(){return this.value},set:function(e){this.$emit("input",e)}}}},a={render:function(){var e=this,t=e.$createElement,s=e._self._c||t;return s("div",{staticClass:"form__group",class:{fill:e.name.length>0}},[s("input",{directives:[{name:"model",rawName:"v-model",value:e.name,expression:"name"}],staticClass:"form__input",class:{invalid:e.v.$dirty&&!e.v.required||e.v.$dirty&&!e.v.minLength},attrs:{id:e.id,name:"name"},domProps:{value:e.name},on:{change:function(t){return e.v.$touch()},input:function(t){t.target.composing||(e.name=t.target.value)}}}),s("label",{staticClass:"form__label",attrs:{for:e.id}},[e._v(e._s(e.label))]),e.v.$dirty&&!e.v.required?s("span",{staticClass:"form__error"},[e._v("Обязательно поле")]):e.v.$dirty&&!e.v.minLength?s("span",{staticClass:"form__error"},[e._v("Минимальное количество символов "+e._s(e.v.$params.minLength.min))]):e._e()])},staticRenderFns:[]},r=s("VU/8")(i,a,!1,null,null,null);t.a=r.exports}});
//# sourceMappingURL=5.6a1e9d1e2413df591572.js.map