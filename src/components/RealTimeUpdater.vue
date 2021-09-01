<script>
import { mapGetters, mapActions } from 'vuex'

const INTERVAL_DIALOG_MS = 2000;
const INTERVAL_NOTIFICATIONS_MS = 5000;

export default {
  mounted () {
    this.intervalForMessages = setInterval(() => {
      if (this.activeDialog) {
        this.loadFreshMessages(this.activeDialog.id)
        this.apiLoadAllDialogs()
      }
      this.apiUnreadedMessages()
    }, INTERVAL_DIALOG_MS)

    this.intervalForNotifications = setInterval(() => {
      this.apiNotifications()
    }, INTERVAL_NOTIFICATIONS_MS)
  },
  computed: {
    ...mapGetters('profile/dialogs', ['activeDialog']),
  },
  methods: {
    ...mapActions('profile/dialogs', ['loadFreshMessages', 'apiLoadAllDialogs', 'apiUnreadedMessages']),
    ...mapActions('profile/notifications', ['apiNotifications']),
  },
  beforeDestroy () {
    window.clearInterval(this.intervalForMessages);
    window.clearInterval(this.intervalForNotifications);
  },
  render: () => null,
}
</script>
