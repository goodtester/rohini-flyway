<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">New message</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <form>
          <div class="form-group">
            <label for="flight" class="col-form-label">Book Flight</label>
            <input type="text" class="form-control" id="flight" disabled=disabled>
          </div>
          <div class="form-group">
            <label for="message-text" class="col-form-label">Name on card</label>
            <input type="text" class="form-control" id="name">
          </div>
          <div class="form-group">
            <label for="message-text" class="col-form-label">Card Number</label>
            <input type="text" class="form-control" id="cardNumber">
          </div>
            <div class="form-group">
              <label for="message-text" class="col-form-label">Expiry Date</label>
              <input type="text" class="form-control" id="expiryDate">
            </div>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
        <button type="button" class="btn btn-primary">Book Now</button>
      </div>
    </div>
  </div>
</div>

<script type="text/javascript">
    $('#exampleModal').on('show.bs.modal', function (event) {
      var button = $(event.relatedTarget)
      var flightDetails = button.data('whatever')
      var modal = $(this)
      modal.find('.modal-title').text('Book Flight ' + flightDetails)
      modal.find('#flight').val(flightDetails)
    })
</script>
