<template lang="pug">
  form.friends-possible(@submit.prevent="onSearchUsers" action="#")
    h4.friends-possible__title Параметры поиска
    .friends-search
      .friends-search__row
        .friends-search__block
          label.search__label(for="friends-search-name") Имя:
          input.search__input(type="text" id="friends-search-name" v-model="first_name")
        .friends-search__block
          label.search__label(for="friends-search-lastname") Фамилия:
          input.search__input(type="text" id="friends-search-lastname" v-model="last_name")
      .friends-search__block
        label.search__label Возраст:
        .search__row
          select.select.friends-search__select(v-model.number="age_from")
            option(value="null" disabled) От
            option(value="18") От 18
            option(value="30") От 30
            option(value="") Все
          span.search__age-defis —
          select.select.friends-search__select(v-model.number="age_to")
            option(value="null" disabled) До
            option(value="45") До 50
            option(value="") Все
      .friends-search__block
        label.search__label Регион:
        .search__row
          select.select.friends-search__select(v-model="country_id")
            option(value="null" disabled) Страна
            option(v-for="country in this.getCountries" :key="country.id") {{ country.title }}
            option(value="") Все

          select.select.friends-search__select(v-model="city_id")
            option(value="null" disabled) Город
            option(v-for="city in this.getCities" :key="city.id") {{ city.title }}
            option(value="") Все
    button.friends-possible__btn(type="submit")
      simple-svg(:filepath="'/static/img/search.svg'")
      span.friends-possible__link Искать друзей
</template>

<script>
import { mapActions, mapGetters } from 'vuex'
export default {
  name: 'FriendsSearch',
  data: () => ({
    first_name: null,
    last_name: null,
    age_from: null,
    age_to: null,
    country_id: null,
    city_id: null,
    offset: 0,
    itemPerPage: 20,
  }),
  computed: {
    ...mapGetters('platform/countries', ['getCountries']),
    ...mapGetters('platform/cities', ['getCities']),
  },
  methods: {
    ...mapActions('global/search', ['searchUsers', 'clearSearch']),
    ...mapActions('platform/countries', ['apiCountries']),
    ...mapActions('platform/cities', ['apiCities']),
    onSearchUsers() {
      let { first_name, last_name, age_from, age_to, country_id, city_id } = this;
      this.searchUsers({ first_name, last_name, age_from, age_to, country_id, city_id })
    },
  },
  beforeDestroy() {
    this.clearSearch()
  },
  created() {
    this.apiCountries();
    this.apiCities();
  }
}
</script>

<style lang="stylus">
@import '../../assets/stylus/base/vars.styl';

.friends-search {
  margin-top: 25px;
  padding-top: 20px;
  margin-bottom: 30px;
  border-top: 1px solid #E6E6E6;
}

.friends-search__row {
  @media (max-width: breakpoint-xl) {
    display: flex;

    .friends-search__block {
      flex: auto;
    }

    .friends-search__block + .friends-search__block {
      margin-top: 0;
      margin-left: 12px;
    }
  }
}

.friends-search__row + .friends-search__block {
  margin-top: 15px;
}

.friends-search__block {
  &+& {
    margin-top: 15px;
  }
}

.friends-search__select {
  display: block;
  width: 100%;

  &+& {
    margin-left: 12px;
  }
}
</style>
