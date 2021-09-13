<template lang="pug">
  .friends.inner-page
    .inner-page__main
      .friends__header
        template(v-if="!friends.first_name || !friends.first_name")
          h2.friends__title Друзей нет
        template(v-else)
          h2.friends__title Мои друзья
        .friends__search
          .friends__search-icon
            simple-svg(:filepath="'/static/img/search.svg'")
          input.friends__search-input(placeholder="Начните вводить имя друга..." v-model="first_name")
      .friends__list
        template(v-if="friends.length || friends.info")
          friends-block(v-for="friend in friends.info" :key="friend.info.id" :info="friend")
        //- template(v-else)
        //-   friends-block
        template
          h2.friends__title Заявки в друзья
          friends-request
        //- template(v-else)
          h2.friends__title Заявок в друзья нет

    .inner-page__aside
      friends-possible
</template>

<script>
import store from '@/store'
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
    ...mapGetters('profile/friends', ['getResultById']),
    ...mapGetters('profile/friends', ['getResult']),
    friends() {
      // return this.first_name.length === 0
      //   ? this.getResultById('friends')
      //   : this.getResultById('friends').filter(
      //       el => el.first_name.toLowerCase().indexOf(this.first_name.toLowerCase()) !== -1
      //     )
      return this.getResultById
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
