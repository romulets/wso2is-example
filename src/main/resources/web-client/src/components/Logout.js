import { withCookies } from 'react-cookie'
import React, { Component } from 'react'

class Logout extends Component {

  constructor (props) {
    super(props)

    this.logout = this.logout.bind(this)
  }

  logout () {
    const { cookies } = this.props
    cookies.remove('accessToken')
    window.location = window.location.pathname
  }

  render () {
    return (
      <button onClick={this.logout}> Logout </button>
    )
  }
}

export default withCookies(Logout)
