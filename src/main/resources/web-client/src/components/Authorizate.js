import React, { Component } from 'react'
import queryString from 'query-string'

export default class Authorizate extends Component {

  state = {
    code: '',
    consumerKey: ''
  }

  componentDidMount () {
    const { code, consumerKey } = this.props

    this.setState({code, consumerKey})
    this.fetchAuthUri(code, consumerKey)
  }

  fetchAuthUri (code, consumerKey) {

    const callbackUri = "http://localhost:8080/wso2Example"
    const authPath    = "http://localhost:8080/wso2Example/api/authorizate"
    const requestUri  = `${authPath}?consumerKey=${consumerKey}&callbackUri=${callbackUri}`

    fetch(requestUri)
      .then(response => response.json())
      .then(message => {
        console.log(message)
      })
      .catch(error => {
        console.error(error)
      })
  }

  render () {
    return (
      <p> Authenticado </p>
    )
  }
}
