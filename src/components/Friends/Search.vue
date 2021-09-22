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
            option(value="45") От 45
            option(value="") Все
          span.search__age-defis —
          select.select.friends-search__select(v-model.number="age_to")
            option(value="null" disabled) До
            option(value="45") До 45
            option(value="") Все
            //- option(value="36") До 36
      //- .friends-search__block
      //-   label.search__label Регион:
      //-   .search__row
      //-     select.select.friends-search__select(v-model="country")
      //-       option(value="null" disabled) Страна
      //-       option Россия
      //-       option Англия
      //-       option США
      //-     select.select.friends-search__select(v-model="city")
      //-       option(value="null" disabled) Город
      //-       option Москва
      //-       option Лондон
      //-       option Техас
      .friends-search__block
        label.search__label Регион:
        .search__row
          select.select.friends-search__select(v-model="country_id")
            option(value="null" disabled) Страна
            option(value="Россия") Россия
            option(value="Англия") Англия
            option(value="США") США
            option(value="") Все

          select.select.friends-search__select(v-model="city_id")
            option(value="null" disabled) Город
            option(value="Москва") Москва
            option(value="Лондон") Лондон
            option(value="Техас") Техас
            option(value="") Все
    button.friends-possible__btn(type="submit")
      simple-svg(:filepath="'/static/img/search.svg'")
      span.friends-possible__link Искать друзей
</template>

<script>
import { mapActions } from 'vuex'
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
    itemPerPage: 20
  }),
  methods: {
    ...mapActions('global/search', ['searchUsers', 'clearSearch']),
    onSearchUsers() {
      let { first_name, last_name, age_from, age_to, country_id, city_id } = this;
      this.searchUsers({ first_name, last_name, age_from, age_to, country_id, city_id })
    }
  },
  beforeDestroy() {
    this.clearSearch()
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
