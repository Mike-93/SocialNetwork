webpackJsonp([20],{"N9p+":function(e,t,s){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var i=s("Dd8w"),n=s.n(i),r=s("NYxO"),a=s("UBpT"),d=s("CqtB"),l={name:"FriendsApplication",computed:n()({},Object(r.c)("profile/friends",["getResult"]),{requestFriends:function(){return this.getResult}}),methods:n()({},Object(r.b)("profile/friends",["apiAddFriends","apiDeclineFriendRequest"])),mounted:function(){0===this.requestFriends.length&&this.apiRequest()}},u={render:function(){var e=this,t=e.$createElement,s=e._self._c||t;return e.requestFriends.request.length>0?s("div",{staticClass:"friends-application"},[s("ul",{staticClass:"friends-application__list"},e._l(e.requestFriends.request,function(t){return s("li",{key:t.id,staticClass:"friends-application__item"},[s("div",{staticClass:"friends-application__pic"},[t.photo?s("img",{attrs:{src:t.photo,alt:t.first_name}}):s("img",{attrs:{src:"/static/img/user/2.webp",alt:t.first_name}})]),s("router-link",{staticClass:"friends-application__name",attrs:{to:{name:"ProfileId",params:{id:t.id}}}},[e._v(e._s(t.first_name+" "+t.last_name))]),s("a",{staticClass:"friends-application__link",attrs:{href:"#"},on:{click:function(s){return s.preventDefault(),e.apiAddFriends(t.id)}}},[e._v("Добавить")]),s("br"),s("br"),s("a",{staticClass:"friends-application__link",attrs:{href:"#"},on:{click:function(s){return s.preventDefault(),e.apiDeclineFriendRequest(t.id)}}},[e._v("Отклонить")])],1)}),0)]):e._e()},staticRenderFns:[]};var c=s("VU/8")(l,u,!1,function(e){s("mOjb")},null,null).exports,f={name:"Friends",components:{FriendsPossible:a.a,FriendsBlock:d.a,FriendsRequest:c},data:function(){return{first_name:""}},computed:n()({},Object(r.c)("profile/friends",["getResult"]),{userFriends:function(){return this.getResult}},Object(r.c)("profile/friends",["getResultById","getResult"]),{requestFriends:function(){return this.getResultById("request")},resultFriends:function(){return this.getResultById("friends")}}),methods:n()({},Object(r.b)("profile/friends",["apiRequestFriends","apiResultFriends","apiFriends"])),mounted:function(){this.apiRequestFriends(),this.apiResultFriends()},beforeRouteEnter:function(e,t,s){s(function(e){e.apiFriends()})}},o={render:function(){var e=this,t=e.$createElement,s=e._self._c||t;return s("div",{staticClass:"friends inner-page"},[s("div",{staticClass:"inner-page__main"},[s("div",{staticClass:"friends__header"},[e.userFriends.friends.length?s("h2",{staticClass:"friends__title"},[e._v("Список друзей")]):s("h2",{staticClass:"friends__title"},[e._v("Друзей нет")]),s("div",{staticClass:"friends__search"},[s("div",{staticClass:"friends__search-icon"},[s("simple-svg",{attrs:{filepath:"/static/img/search.svg"}})],1),s("input",{directives:[{name:"model",rawName:"v-model",value:e.first_name,expression:"first_name"}],staticClass:"friends__search-input",attrs:{placeholder:"Начните вводить имя друга..."},domProps:{value:e.first_name},on:{input:function(t){t.target.composing||(e.first_name=t.target.value)}}})])]),e.userFriends.friends.length?s("div",{staticClass:"friends__list"},e._l(e.userFriends.friends,function(e){return s("friends-block",{key:e.id,attrs:{info:e}})}),1):e._e(),e.userFriends.request.length?[s("h2",{staticClass:"friends__title"},[e._v("Заявки в друзья")]),e._l(e.userFriends.request,function(e){return s("friends-request",{key:e.id,attrs:{info:e}})})]:[s("h2",{staticClass:"friends__title"},[e._v("Заявок в друзья нет")])]],2),s("div",{staticClass:"inner-page__aside"},[s("friends-possible")],1)])},staticRenderFns:[]},p=s("VU/8")(f,o,!1,null,null,null);t.default=p.exports},mOjb:function(e,t){}});
//# sourceMappingURL=20.bb8470e995d221462375.js.map