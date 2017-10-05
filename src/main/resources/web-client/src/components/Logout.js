import { withCookies } from 'react-cookie'
import React, { Component } from 'react'

class Logout extends Component {

  constructor (props) {
    super(props)

    this.state = {
      logoutUri: ''
    }

    this.logout = this.logout.bind(this)
  }

  componentDidMount () {
    this.fetchLogoutUri()
  }

  logout () {
    const { cookies } = this.props
    cookies.remove('accessToken')
    window.location = this.state.logoutUri
  }

  fetchLogoutUri () {
    const callbackUri = 'http://localhost:8080/wso2Example'
    const authPath = 'http://localhost:8080/wso2Example/api/authentication/logout-uri'
    const requestUri = `${authPath}?callbackUri=${callbackUri}`

    fetch(requestUri)
      .then(response => response.json())
      .then(message => {
        this.setState({ logoutUri: message.uri })
      }).catch(error => {
        console.error(error)
      })
  }

  render () {
    return (
      <button onClick={this.logout}> Logout </button>
    )
  }
}

export default withCookies(Logout)
