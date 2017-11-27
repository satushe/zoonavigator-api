/*
 * Copyright (C) 2017  Ľuboš Kozmon
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package action

import com.elkozmon.zoonavigator.core.action.ActionHandler
import com.elkozmon.zoonavigator.core.action.actions._
import action.dispatcher.DefaultActionDispatcherProvider
import org.apache.curator.framework.CuratorFramework
import shapeless.HNil

trait ActionModule {

  def createZNodeActionHandler(
      curatorFramework: CuratorFramework
  ): ActionHandler[CreateZNodeAction]

  def deleteZNodeRecursiveActionHandler(
      curatorFramework: CuratorFramework
  ): ActionHandler[DeleteZNodeRecursiveAction]

  def forceDeleteZNodeRecursiveActionHandler(
      curatorFramework: CuratorFramework
  ): ActionHandler[ForceDeleteZNodeRecursiveAction]

  def getZNodeAclListActionHandler(
      curatorFramework: CuratorFramework
  ): ActionHandler[GetZNodeAclAction]

  def getZNodeDataActionHandler(
      curatorFramework: CuratorFramework
  ): ActionHandler[GetZNodeDataAction]

  def getZNodeMetaActionHandler(
      curatorFramework: CuratorFramework
  ): ActionHandler[GetZNodeMetaAction]

  def getZNodeChildrenActionHandler(
      curatorFramework: CuratorFramework
  ): ActionHandler[GetZNodeChildrenAction]

  def updateZNodeAclListActionHandler(
      curatorFramework: CuratorFramework
  ): ActionHandler[UpdateZNodeAclListAction]

  def updateZNodeAclListRecursiveActionHandler(
      curatorFramework: CuratorFramework
  ): ActionHandler[UpdateZNodeAclListRecursiveAction]

  def updateZNodeDataActionHandler(
      curatorFramework: CuratorFramework
  ): ActionHandler[UpdateZNodeDataAction]

  val actionDispatcherProvider = new DefaultActionDispatcherProvider(
    curatorFramework =>
      createZNodeActionHandler(curatorFramework) ::
        deleteZNodeRecursiveActionHandler(curatorFramework) ::
        forceDeleteZNodeRecursiveActionHandler(curatorFramework) ::
        getZNodeAclListActionHandler(curatorFramework) ::
        getZNodeDataActionHandler(curatorFramework) ::
        getZNodeMetaActionHandler(curatorFramework) ::
        getZNodeChildrenActionHandler(curatorFramework) ::
        updateZNodeAclListActionHandler(curatorFramework) ::
        updateZNodeAclListRecursiveActionHandler(curatorFramework) ::
        updateZNodeDataActionHandler(curatorFramework) ::
      HNil
  )
}