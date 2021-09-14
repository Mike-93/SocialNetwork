<template lang="pug">
  .friends.inner-page
    .inner-page__main
      .friends__header
        h2.friends__title(v-if="!userFriends.friends.length") Друзей нет
        h2.friends__title(v-else) Список друзей
        .friends__search
          .friends__search-icon
            simple-svg(:filepath="'/static/img/search.svg'")
          input.friends__search-input(placeholder="Начните вводить имя друга..." v-model="first_name")
      .friends__list(v-if="userFriends.friends.length")
        friends-block(v-for="info in userFriends.friends" :key="info.id" :info="info")

      //- template(v-if="!userFriends.request.length")
      //-   h2.friends__title Заявок в друзья нет
      //- template(v-else)
      //-   h2.friends__title Заявки в друзья
      //-   friends-request(v-for="info in userFriends.request" :key="info.id" :info="info")

    .inner-page__aside
      friends-possible
</template>

<script>
import { mapGetters, mapActions } from 'vuex'
import FriendsPossible from '@/components/Friends/Possible'
import FriendsBlock from '@/components/Friends/Block'
import FriendsRequest from '@/components/Friends/Request'
export default {
  name: 'Friends',
  components: { FriendsPossible, FriendsBlock, FriendsRequest},
  data: () => ({
    first_name: '',
  }),
  computed: {
    // ...mapGetters('profile/friends', ['getResultById']),
    ...mapGetters('profile/friends', ['getResult']),
    // friends() {
      // return this.first_name.length === 0
      //   ? this.getResultById('friends')
      //   : this.getResultById('friends').filter(
      //       el => el.first_name.toLowerCase().indexOf(this.first_name.toLowerCase()) !== -1
      //     )
    // },
    userFriends() {
      return this.getResult;
    },
  },
  methods: {
    ...mapActions('profile/friends', ['apiFriends'])
  },
  beforeRouteEnter(to, from, next) {
    next(vm => {
      vm.apiFriends()
    })
  }
}
</script>
